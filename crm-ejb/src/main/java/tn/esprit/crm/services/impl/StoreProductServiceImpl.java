package tn.esprit.crm.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import tn.esprit.crm.dao.IStoreProductDao;
import tn.esprit.crm.entities.StoreProduct;
import tn.esprit.crm.services.IStoreProductService;

@Stateless
public class StoreProductServiceImpl implements IStoreProductService {
	@PersistenceContext(unitName="crm-ejb")
	
	
	@EJB
	IStoreProductDao spDao;

	@Override
	public StoreProduct save(StoreProduct sp) {
	
		return spDao.save(sp);
	}

	@Override
	public StoreProduct update(StoreProduct sp) {
		
		return spDao.update(sp);
	}

	@Override
	public List<StoreProduct> selectAll() {
		
		return spDao.selectAll();
	}

	@Override
	public List<StoreProduct> selectBy(String param, String value) {
	
		return spDao.selectBy(param, value);
	}

	@Override
	public List<StoreProduct> selectAll(String sortField, String sort) {
		
		return spDao.selectAll(sortField, sort);
	}

	@Override
	public StoreProduct getById(Long id) {
		
		return spDao.getById(id);
	}

	@Override
	public boolean remove(Long id) {
		
		if (id!=0) {
			spDao.remove(id);
			return true;
			}
			return false;
	}

	@Override
	public int UpdateStoreprodQte(Long idprod, Long idStore, int qte) {
		return spDao.UpdateStoreprodQte(idprod, idStore, qte);
	}

	@Override
	public List<StoreProduct> StatisticsStore() {
		
		return spDao.StatisticsStore();
	}

	@Override
	public void ActivateDispo(Long idsp, Long id_prod) {
		spDao.ActivateDispo(idsp, id_prod);
		
	}

	@Override
	public void DesactivateDispo(Long idsp, Long id_prod) {
		spDao.DesactivateDispo(idsp, id_prod);
		
	}
	
	
	
}
