package tn.esprit.crm.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pack implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String PackName;
	private Date PackStartDate;
	private Date PackEndDate;
	private String PackDescription;
	private int reduction_amount;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "packs")
	private Set<Pack_Product> pack_products;

	public Pack(String packName, Date packStartDate, Date packEndDate, String packDescription) {
		super();
		PackName = packName;
		PackStartDate = packStartDate;
		PackEndDate = packEndDate;
		PackDescription = packDescription;
	}



	public Pack() {
		super();
	}


	@XmlAttribute(name="id" , required=true)
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	@XmlElement(name="PackName")
	public String getPackName() {
		return PackName;
	}



	public void setPackName(String packName) {
		PackName = packName;
	}


	@XmlElement(name="PackStartDate")
	public Date getPackStartDate() {
		return PackStartDate;
	}



	public void setPackStartDate(Date packStartDate) {
		PackStartDate = packStartDate;
	}


	@XmlElement(name="PackEndDate")
	public Date getPackEndDate() {
		return PackEndDate;
	}



	public void setPackEndDate(Date packEndDate) {
		PackEndDate = packEndDate;
	}


	@XmlElement(name="PackDescription")
	public String getPackDescription() {
		return PackDescription;
	}



	public void setPackDescription(String packDescription) {
		PackDescription = packDescription;
	}



	public int getReduction_amount() {
		return reduction_amount;
	}



	public void setReduction_amount(int reduction_amount) {
		this.reduction_amount = reduction_amount;
	}

	
}
