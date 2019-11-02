package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Pack;
import tn.esprit.crm.entities.Store;

@Local

public interface IPackService {

	public Pack save (Pack pack);
	public List<Pack> selectAll();
	public Pack update(Pack pack);
	public boolean remove(Long id);
	public Pack getById(Long id);
}
