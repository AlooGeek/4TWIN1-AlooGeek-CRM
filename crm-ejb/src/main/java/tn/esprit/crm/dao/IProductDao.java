package tn.esprit.crm.dao;

import javax.ejb.Remote;

import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.StoreProduct;

@Remote
public interface IProductDao extends IGenericDao<Product> {

	public int updateQte(Long id,int qte);
}
