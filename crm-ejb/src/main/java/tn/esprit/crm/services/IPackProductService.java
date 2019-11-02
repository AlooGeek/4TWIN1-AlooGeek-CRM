package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Pack_Product;

@Local
public interface IPackProductService {

	public Pack_Product save (Pack_Product pp);
	public List<Pack_Product> selectAll();
	public Pack_Product update(Pack_Product pp);
	public boolean remove(Long id);
	public Pack_Product getById(Long id);
	public List<Double> getPriceOfPack();

}
