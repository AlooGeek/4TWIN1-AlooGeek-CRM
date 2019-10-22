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
		if(id!=0) {
			 discountDao.remove(id);
			 return true;
		}
		return false;
	}

	
	


}
