package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Offer;
import tn.esprit.crm.services.IOfferService;

@Stateless
public class OfferServiceImpl implements IOfferService{

	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	//private IDiscountDao discountDao;
	EntityManager em;
	
	@Override
	public Offer save(Offer offer) {
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

}
