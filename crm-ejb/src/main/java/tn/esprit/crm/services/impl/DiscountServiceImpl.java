package tn.esprit.crm.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.crm.dao.IDiscountDao;
import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.services.IDiscountService;

@Stateless
public class DiscountServiceImpl implements IDiscountService  {	
	
	@PersistenceContext(unitName="crm-ejb") //l esm men persistance.xml
	
	private IDiscountDao discountDao;
	


	@Override
	public Discount save(Discount discount) {
		discountDao.save(discount);
		return discount;
		
	}

}
