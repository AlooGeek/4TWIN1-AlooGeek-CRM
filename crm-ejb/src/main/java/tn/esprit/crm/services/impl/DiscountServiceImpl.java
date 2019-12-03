package tn.esprit.crm.services.impl;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.crm.dao.IDiscountDao;
import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.User;
import tn.esprit.crm.services.IDiscountService;

@Stateless
public class DiscountServiceImpl implements IDiscountService  {	
	
	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	//private IDiscountDao discountDao;
	EntityManager em;
	
	@EJB
	IDiscountDao discountDao;
	
	@Override
	public Discount save(Discount d) {
	return discountDao.save(d);
	}

	@Override
	public List<Discount> selectAll() {
		
		return discountDao.selectAll();
	}

	@Override
	public Discount update(Discount discount) {
	return discountDao.update(discount);
	
	}


	@Override
	public boolean remove(Long id) {
		//List<Product>list=em.createQuery("SELECT p FROM Product u where u.discount:=id ",Product.class).getResultList();
		
		if(id!=0) {
			Query q = em.createQuery("update Product p set p.discount=NULL where p.discount.id=:id"); //verifier si la discount est affecter a un produit
			q.setParameter("id", id);
			q.executeUpdate();
			 discountDao.remove(id);
			 return true;
		}
		return false;
	}

	@Override
	public Discount getById(Long id) {
	return discountDao.getById(id);
	}

	@Override
	public boolean DeleteExpiredDiscount() {
		Query q=em.createQuery("DELETE from Discount d where d.enddate =CURDATE()");
		q.executeUpdate();
		System.out.println("Discounts deleted");
		return true;

		
	}

	@Override
	public List<Discount> StatistiqueDiscount() {
		Query q=em.createQuery("SELECT p.discount.reduction_amount ,COUNT(*)  from Product p group by(p.discount.id)");
		return q.getResultList();			
		}

	@Override
	public List<Product> getProductWithoutDiscount() {
		List<Product> products=em.createQuery("SELECT p FROM Product p where p.discount=NULL",Product.class).getResultList();
		return products;
	}
		
		
		

	
	


}
