package tn.esprit.crm.dao.impl;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import tn.esprit.crm.dao.IStoreProductDao;
import tn.esprit.crm.entities.StoreProduct;

@Stateless
public class StoreProductImpl extends GenericDaoImpl<StoreProduct> implements IStoreProductDao {

	@Override
	public int UpdateStoreprodQte(Long idprod, Long idStore, int qte) {
		Query query = em.createQuery("update StoreProduct sp set sp.qte=sp.qte-:qte where sp.stores=:idStore and sp.products=:idprod");
		query.setParameter("qte", qte);
		query.setParameter("idStore", idStore);
		query.setParameter("idprod", idprod);
		return query.executeUpdate();
		
	}

	@Override
	public List<StoreProduct> StatisticsStore() {
		Query query = em.createQuery("select sp.stores,count(sp) from StoreProduct sp group by sp.stores");
		return query.getResultList();
		
	}

}
