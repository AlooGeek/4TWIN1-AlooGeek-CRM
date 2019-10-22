package tn.esprit.crm.dao.impl;

import javax.ejb.Stateless;

import tn.esprit.crm.dao.IProductDao;
import tn.esprit.crm.entities.Product;

@Stateless
public class ProductDaoImpl extends GenericDaoImpl<Product> implements IProductDao {

}
