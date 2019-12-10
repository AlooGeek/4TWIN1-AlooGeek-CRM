package tn.esprit.crm.services.impl;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.Null;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Offer;
import tn.esprit.crm.entities.User;
import tn.esprit.crm.services.IOfferService;

@Stateless
public class OfferServiceImpl implements IOfferService{

	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	//private IDiscountDao discountDao;
	EntityManager em;
	
	@Override
	public Offer save(Offer offer) {
	 String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	 StringBuilder builder = new StringBuilder();
	 int count=10;
	 String code="";
	 while (count-- != 0) {
	 int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
	code= builder.append(ALPHA_NUMERIC_STRING.charAt(character)).toString();
	 }
	 offer.setOffCode(code);
		em.persist(offer);
		return offer;
	}

	@Override
	public List<Offer> selectAll() {
		
		List<Offer> offers=em.createQuery("SELECT o FROM Offer o  ",Offer.class).getResultList();
		return offers;
	}

	@Override
	public Offer update(Offer offer) {
		em.merge(offer);
		return offer;
	}

	@Override
	public boolean Delete(String OffCode) {
		for (Offer o:this.selectAll()) {
			if (o.getOffCode().equals(OffCode)) {
				em.remove(o);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<User> getBestUsers() {
		List<User> users=em.createQuery("SELECT u FROM User u where u.userScore >= 100 and u.offer=NULL and u.role!=1",User.class).getResultList();
		return users;
	}

	@Override
	public int AddOfferToUser( String OffCode) {
		List<User>list=getBestUsers();
		int i = 0;
		if(list.size()> 0 ) {
		for (User user : list) {
			user.setOffer(em.find(Offer.class, OffCode));
			em.merge(user);
			em.flush();
		}
		i = 1;
		}
		return i;
		
		
	}

	@Override
	public Offer getById(String OffCode) {
		return em.find(Offer.class, OffCode);
	}

	@Override
	public void setUsersNull() {
		List<User>list=em.createQuery("SELECT u FROM User u where u.offer is NOT NULL ",User.class).getResultList();
		if(list.size()> 0 ) {
		for (User user : list) {
			user.setOffer(null);
			em.merge(user);
			em.flush();
		}
	
		}

		
	}

	@Override
	public Boolean AddScoreToUser(Long idUser) {
		
	//	User u =em.createQuery("SELECT u FROM User u where u.id =:idUser",User.class).getSingleResult();
		Query q=em.createQuery("SELECT u FROM User u where u.id =:idUser");
		q.setParameter("idUser", idUser);
		User u = (User) q.getSingleResult();
		if(u!=null) {
			
			u.setUserScore(u.getUserScore()+25);
			return true;
					}
		else return false;
		
		/*Query q=em.createQuery("SELECT u FROM User u where u.id :=idUser");
		q.setParameter("idUser",idUser);
		q.executeUpdate();*/
				
	}

	@Override
	public List<User> getBestUsersWithOffer() {
		List<User> users=em.createQuery("SELECT u FROM User u where u.userScore >= 100 and u.offer!=NULL",User.class).getResultList();
		return users;
	}

	@Override
	public Offer VerifyCoupon(String coupon) {
		Query q=em.createQuery("SELECT u.offer FROM User u where u.offer.OffCode =:coupon and u.offer.OffEndDate >= current_date()");
		q.setParameter("coupon",coupon);
		Offer f =(Offer) q.getSingleResult();
		if(f!=null) {
			return f;
		}
		else  {
			return null ;
		}
	}

}
