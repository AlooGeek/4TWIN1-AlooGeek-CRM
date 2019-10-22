package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import tn.esprit.crm.dao.IStoreDao;
import tn.esprit.crm.entities.Store;
import tn.esprit.crm.services.IStoreService;

@Stateless
public class StoreServiceImpl implements IStoreService{
	@PersistenceContext(unitName="crm-ejb")

	
	@EJB
	IStoreDao storeDao;
	
	
	@Override
	public Store save(Store store) {
		storeDao.save(store);	 
		return store;	
	}

	@Override
	public Store update(Store store) {
		storeDao.update(store);
		return store;
	}

	@Override
	public List<Store> selectAll() {
		return storeDao.selectAll();
	}

	@Override
	public List<Store> selectBy(String param, String value) {
		
		return storeDao.selectBy(param, value);
	}

	@Override
	public List<Store> selectAll(String sortField, String sort) {
		return storeDao.selectAll(sortField, sort);
	}

	@Override
	public Store getById(Long id) {
		return storeDao.getById(id);
	}

	@Override
	public boolean remove(Long id) {
		
		if (id!=0) {
		storeDao.remove(id);
		return true;
		}
		return false;
	
		
	}



}
