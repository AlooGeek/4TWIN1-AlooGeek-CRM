package tn.esprit.crm.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Offer implements Serializable {
	
	@Id
	private String OffCode;
	private String OffName;
	private Date OffStartDate;
	private Date OffEndDate;
	private int OffReduction_amount;
	private String OffDescription;
	
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "offer", cascade = CascadeType.ALL)
	private List<User> user;
	
	public String getOffCode() {
		return OffCode;
	}
	public void setOffCode(String offCode) {
		OffCode = offCode;
	}
	public String getOffName() {
		return OffName;
	}
	public void setOffName(String offName) {
		OffName = offName;
	}
	public Date getOffStartDate() {
		return OffStartDate;
	}
	public void setOffStartDate(Date offStartDate) {
		OffStartDate = offStartDate;
	}
	public Date getOffEndDate() {
		return OffEndDate;
	}
	public void setOffEndDate(Date offEndDate) {
		OffEndDate = offEndDate;
	}
	public int getOffReduction_amount() {
		return OffReduction_amount;
	}
	public void setOffReduction_amount(int offReduction_amount) {
		OffReduction_amount = offReduction_amount;
	}
	public String getOffDescription() {
		return OffDescription;
	}
	public void setOffDescription(String offDescription) {
		OffDescription = offDescription;
	}
	public Offer(String offCode, String offName, Date offStartDate, Date offEndDate, int offReduction_amount,
			String offDescription) {
		super();
		OffCode = offCode;
		OffName = offName;
		OffStartDate = offStartDate;
		OffEndDate = offEndDate;
		OffReduction_amount = offReduction_amount;
		OffDescription = offDescription;
	}
	public Offer() {
		super();
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	
	

}
