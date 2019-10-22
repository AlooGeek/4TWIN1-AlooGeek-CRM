package tn.esprit.crm.dao;

import javax.ejb.Remote;

import tn.esprit.crm.entities.Product;

@Remote
public interface IProductDao extends IGenericDao<Product> {

}
