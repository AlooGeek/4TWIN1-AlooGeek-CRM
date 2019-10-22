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
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "packs", cascade = CascadeType.ALL)
<<<<<<< HEAD
	private List<pack_product> packproducts;
=======
	private Set<Pack_Product> pack_products;
>>>>>>> 7deb84a96367460c984886d0c00aa670b45997f2
	
	

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



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPackName() {
		return PackName;
	}



	public void setPackName(String packName) {
		PackName = packName;
	}



	public Date getPackStartDate() {
		return PackStartDate;
	}



	public void setPackStartDate(Date packStartDate) {
		PackStartDate = packStartDate;
	}



	public Date getPackEndDate() {
		return PackEndDate;
	}



	public void setPackEndDate(Date packEndDate) {
		PackEndDate = packEndDate;
	}



	public String getPackDescription() {
		return PackDescription;
	}



	public void setPackDescription(String packDescription) {
		PackDescription = packDescription;
	}



<<<<<<< HEAD

=======


	
>>>>>>> 7deb84a96367460c984886d0c00aa670b45997f2
	
}
