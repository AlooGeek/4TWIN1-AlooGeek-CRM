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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement

public class Discount implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date startdate;
	private Date enddate;
	private int reduction_amount;
	private String description;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy = "discount", cascade = CascadeType.ALL)
	private Set<Product> product;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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


	@Override
	public String toString() {
		return "Discount [id=" + id + ", name=" + name + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", reduction_amount=" + reduction_amount + ", description=" + description + ", product=" + product
				+ "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discount other = (Discount) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (enddate == null) {
			if (other.enddate != null)
				return false;
		} else if (!enddate.equals(other.enddate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (reduction_amount != other.reduction_amount)
			return false;
		if (startdate == null) {
			if (other.startdate != null)
				return false;
		} else if (!startdate.equals(other.startdate))
			return false;
		return true;
	}


}
