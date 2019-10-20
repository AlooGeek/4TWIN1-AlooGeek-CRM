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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class Discount implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private Date startdate;
	private Date enddate;
	private int reduction_amount;
	private String description;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="discount")
	private List<Product> product;
	
	public Discount() {
		super();
	}
	

	public Discount(String name, Date startdate, Date enddate, int reduction_amount, String description) {
		super();
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.reduction_amount = reduction_amount;
		this.description = description;
	}

	@XmlAttribute(name="id" , required=true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement(name="StartDate")
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	@XmlElement(name="EndDate")
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@XmlElement(name="Name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="Reduction")
	public int getReduction_amount() {
		return reduction_amount;
	}


	public void setReduction_amount(int reduction_amount) {
		this.reduction_amount = reduction_amount;
	}


	public List<Product> getProduct() {
		return product;
	}


	public void setProduct(List<Product> product) {
		this.product = product;
	}

	
}
