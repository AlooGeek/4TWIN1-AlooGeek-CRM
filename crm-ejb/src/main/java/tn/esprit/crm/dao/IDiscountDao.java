package tn.esprit.crm.dao;

import javax.ejb.Local;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Product;

@Local
public interface IDiscountDao extends IGenericDao<Discount> {

	//public boolean AffecterDiscountToProduct(Product p,int id_discount);
}
