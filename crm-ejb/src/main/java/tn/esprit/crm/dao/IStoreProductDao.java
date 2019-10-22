package tn.esprit.crm.dao;

import javax.ejb.Remote;

import tn.esprit.crm.entities.StoreProduct;



@Remote
public interface IStoreProductDao extends IGenericDao<StoreProduct>   {

}
