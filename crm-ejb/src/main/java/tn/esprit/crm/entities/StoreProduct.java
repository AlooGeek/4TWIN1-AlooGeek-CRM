package tn.esprit.crm.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class StoreProduct implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sp")
	private Long id;
	private int qte;
	private Date dateEntry;
	@ManyToOne
	@JoinColumn(name ="STORE_ID",referencedColumnName ="id_store",insertable=true,updatable=true)
	private Store stores;
	@ManyToOne
	@JoinColumn(name ="PRODUCT_ID",referencedColumnName ="id_product",insertable=true,updatable=true)
	private Product products;
	



	public StoreProduct(Long id, int qte, Date dateEntry, Store stores, Product products) {
		
		this.id = id;
		this.qte = qte;
		this.dateEntry = dateEntry;
		this.stores = stores;
		this.products = products;
	}

	public StoreProduct() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Date getDateEntry() {
		return dateEntry;
	}


	
		public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;
	}

		public Store getStores() {
		return stores;
	}

	public void setStores(Store stores) {
		this.stores = stores;
	}
	
	
	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "StoreProduct [id=" + id + ", qte=" + qte + ", dateEntry=" + dateEntry + ", stores=" + stores
				+ ", products=" + products + "]";
	}


	
	

}
