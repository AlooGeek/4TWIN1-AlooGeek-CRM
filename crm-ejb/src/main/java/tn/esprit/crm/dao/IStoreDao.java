package tn.esprit.crm.dao;

import javax.ejb.Remote;

import tn.esprit.crm.entities.Store;

@Remote
public interface IStoreDao extends IGenericDao<Store> {

}
