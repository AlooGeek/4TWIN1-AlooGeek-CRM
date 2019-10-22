package tn.esprit.crm.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import tn.esprit.crm.entities.Document;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Document_line implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date creation_date;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name ="DOCUMENT_ID",referencedColumnName ="id",insertable=true,updatable=true)
	private Document document;
	
	@ManyToOne
	@JoinColumn(name ="PRODUCT_ID",referencedColumnName ="id_product",insertable=true,updatable=true)
	private Product product;

	public Document_line(int id, Date creation_date, int quantity, Document document, Product product) {
		super();
		this.id = id;
		this.creation_date = creation_date;
		this.quantity = quantity;
		this.document = document;
		this.product = product;
	}

	public Document_line() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Document_line [id=" + id + ", creation_date=" + creation_date + ", quantity=" + quantity + ", document="
				+ document + ", product=" + product + "]";
	}
	

	

}
