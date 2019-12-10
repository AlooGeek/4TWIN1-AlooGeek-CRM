package tn.esprit.crm.dao.impl;


import java.util.List;

import javax.ejb.Stateless;
import javax.mail.Store;
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

		Query query = em.createQuery("select sp.stores.name,count(sp) from StoreProduct sp group by sp.stores");
		return query.getResultList();
		
	}

	@Override
	public void ActivateDispo(Long idsp, Long id_prod) {
		Query query = em.createQuery("update StoreProduct sp set sp.disponible=1 where sp.stores=:idStore and sp.products=:idprod");
		query.setParameter("idStore", idsp);
		query.setParameter("idprod", id_prod);
		query.executeUpdate();
		
	}

	@Override
	public void DesactivateDispo(Long idsp, Long id_prod) {
		Query query = em.createQuery("update StoreProduct sp set sp.disponible=0 where sp.stores=:idStore and sp.products=:idprod");
		query.setParameter("idStore", idsp);
		query.setParameter("idprod", id_prod);
		query.executeUpdate();
		
	}

	@Override
	public List<StoreProduct> listGroupByProduct(Long IdStore) {
		Query query = em.createQuery("select sp from StoreProduct sp where sp.stores.id=:IdStore group by sp.products",StoreProduct.class);
		query.setParameter("IdStore", IdStore);
		return query.getResultList();
	}

	@Override
	public List<StoreProduct> listGroupByStores() {
		Query query = em.createQuery("select sp from StoreProduct sp group by sp.stores");
		return query.getResultList();
	}

}
