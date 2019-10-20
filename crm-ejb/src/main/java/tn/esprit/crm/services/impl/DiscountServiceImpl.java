package tn.esprit.crm.services.impl;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.IDiscountDao;
import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.services.IDiscountService;

@Stateless
public class DiscountServiceImpl implements IDiscountService  {	
	
	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	//private IDiscountDao discountDao;
	EntityManager em;
	
	@Override
	public Discount save(Discount d) {
	em.persist(d);
		return d;
	}

	@Override
	public List<Discount> selectAll() {
		 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA@@@@@@@@@");
	     System.out.println(Discount.class.getName());
		List<Discount> discounts=em.createQuery("from"+Discount.class.getName()).getResultList();
		return discounts;
	}

	
	


}
