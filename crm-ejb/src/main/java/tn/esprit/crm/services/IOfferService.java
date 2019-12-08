package tn.esprit.crm.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Discount;
import tn.esprit.crm.entities.Offer;
import tn.esprit.crm.entities.User;

@Local

public interface IOfferService {

	public Offer save (Offer offer);
	public List<Offer> selectAll();
	public Offer update(Offer offer);
	public boolean Delete(String OffCode);
	public List<User> getBestUsers();
	public List<User> getBestUsersWithOffer();
	public int AddOfferToUser(String OffCode);
	public Offer getById(String OffCode);
	public void setUsersNull();
	public Offer VerifyCoupon(String coupon);
	public Boolean AddScoreToUser(Long idUser);

}
