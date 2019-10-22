package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Offer;

@Local

public interface IOfferService {

	public Offer save (Offer offer);
	public List<Offer> selectAll();
	public Offer update(Offer offer);
	public boolean Delete(String OffCode);
	
}
