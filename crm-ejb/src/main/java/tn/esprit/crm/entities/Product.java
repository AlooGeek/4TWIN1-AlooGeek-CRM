package tn.esprit.crm.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;





@Entity
@XmlRootElement
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private int id;
	private String label;
	private float unitPrice;
	private int qte;
	private int tva;
	
	@OneToMany(mappedBy="products")
	private List<StoreProduct> storeproduct;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Discount discount;
	
	
	public Product(int id, String label, float unit_price, int qte, int tva) {
		
		this.id = id;
		this.label = label;
		this.unitPrice = unit_price;
		this.qte = qte;
		this.tva = tva;
	}


	public Product() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public float getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(float unit_price) {
		this.unitPrice = unit_price;
	}


	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	public int getTva() {
		return tva;
	}


	public void setTva(int tva) {
		this.tva = tva;
	}


	public List<StoreProduct> getStoreproduct() {
		return storeproduct;
	}


	public void setStoreproduct(List<StoreProduct> storeproduct) {
		this.storeproduct = storeproduct;
	}


	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
		this.discount = discount;
	}



	
	
	

}
