package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.crm.entities.StoreProduct;

@Remote
public interface IStoreProductService {

	public StoreProduct save(StoreProduct sp);

	public StoreProduct update(StoreProduct sp);
	
	public List<StoreProduct> selectAll();
	
	public List<StoreProduct> selectBy(String param, String value);
	
	public List<StoreProduct> selectAll(String sortField, String sort);
	
	public StoreProduct getById(Long id);
	
	public boolean remove(Long id);
	
	public int UpdateStoreprodQte(Long idprod, Long idStore, int qte);
	
	public List<StoreProduct> StatisticsStore();
	
	public void ActivateDispo(Long idsp,Long id_prod);
	
	public void DesactivateDispo(Long idsp,Long id_prod);
	
	public List<StoreProduct> listGroupByProduct(Long IdStore);
	
	public List<StoreProduct> listGroupByStores();
}
