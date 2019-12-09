package tn.esprit.crm.dao;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.crm.entities.StoreProduct;



@Remote
public interface IStoreProductDao extends IGenericDao<StoreProduct>   {
	
	public int UpdateStoreprodQte(Long idprod,Long idStore,int qte);
	
	public List<StoreProduct>  StatisticsStore();
	
	public void ActivateDispo(Long idsp,Long id_prod);
	
	public void DesactivateDispo(Long idsp,Long id_prod);
	
	public List<StoreProduct> listGroupByProduct(Long IdStore);
	
	public List<StoreProduct> listGroupByStores();
	
}
