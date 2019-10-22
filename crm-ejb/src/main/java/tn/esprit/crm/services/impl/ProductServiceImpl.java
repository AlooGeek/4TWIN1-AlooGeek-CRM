package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import tn.esprit.crm.dao.IProductDao;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.services.IProductService;


@Stateless
public class ProductServiceImpl implements IProductService {
	
	@PersistenceContext(unitName="crm-ejb")

	
	@EJB
	IProductDao prodDao;

	
	@Override
	public Product save(Product product) {
			 
		return prodDao.save(product);
	}
	
	@Override
	public Product update(Product product) {
		
		return prodDao.update(product);
	}

	@Override
	public List<Product> selectAll() {
	
		return prodDao.selectAll();
	}

	@Override
	public List<Product> selectBy(String param, String value) {
		
		return prodDao.selectBy(param, value);
	}

	@Override
	public List<Product> selectAll(String sortField, String sort) {
		
		return prodDao.selectAll(sortField, sort);
	}

	@Override
	public Product getById(Long id) {
		return prodDao.getById(id);
	}

	@Override
	public boolean remove(Long id) {
		
			if (id!=0) {
				prodDao.remove(id);
				return true;
			}
		
		return false;
	}



}
