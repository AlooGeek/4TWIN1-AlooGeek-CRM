package tn.esprit.crm.dao.impl;

import javax.ejb.Stateless;

import tn.esprit.crm.dao.IDiscountDao;
import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Product;


@Stateless
public class DiscountDaoImpl extends GenericDaoImpl<Discount> implements IDiscountDao {

}
