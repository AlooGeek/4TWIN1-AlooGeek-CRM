package tn.esprit.crm.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;





@Entity
@XmlRootElement
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long id;
	private String label;
	private float unitPrice;
	private int qte;
	private int tva;
<<<<<<< HEAD
	
	@OneToMany(mappedBy="products",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
=======
	@OneToMany(mappedBy="products",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
>>>>>>> 7deb84a96367460c984886d0c00aa670b45997f2
	private Set<StoreProduct> storeproducts;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Discount discount;

<<<<<<< HEAD
	@OneToMany(mappedBy ="productss",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<pack_product> pack_product;
	
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Document_line> doc_lines;
	
=======
	@OneToMany(fetch=FetchType.EAGER,mappedBy="productss",cascade = CascadeType.ALL)
	private Set<Pack_Product> pack_product;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="product",cascade = CascadeType.ALL)
	private Set<Document_line>doc_lines;
>>>>>>> 7deb84a96367460c984886d0c00aa670b45997f2
	
	





	public void setPack_product(Set<pack_product> pack_product) {
		this.pack_product = pack_product;
	}




	public Product(Long id, String label, float unit_price, int qte, int tva) {
		
		this.id = id;
		this.label = label;
		this.unitPrice = unit_price;
		this.qte = qte;
		this.tva = tva;
	}


	public Product() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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

	
<<<<<<< HEAD
=======
	public Set<StoreProduct> getStoreproduct() {
		return storeproducts;
	}


	public void setStoreproduct(Set<StoreProduct> storeproduct) {
		this.storeproducts = storeproduct;
	}
>>>>>>> 7deb84a96367460c984886d0c00aa670b45997f2


	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
		this.discount = discount;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", label=" + label + ", unitPrice=" + unitPrice + ", qte=" + qte + ", tva=" + tva
				+ ", storeproducts=" + storeproducts + ", discount=" + discount + "]";
	}


<<<<<<< HEAD
=======
	public Set<StoreProduct> getStoreproducts() {
		return storeproducts;
	}


	public void setStoreproducts(Set<StoreProduct> storeproducts) {
		this.storeproducts = storeproducts;
	}


	

	public Set<Pack_Product> getPack_product() {
		return pack_product;
	}


	public void setPack_product(Set<Pack_Product> pack_product) {
		this.pack_product = pack_product;
	}


	public Set<Document_line> getDoc_lines() {
		return doc_lines;
	}


	public void setDoc_lines(Set<Document_line> doc_lines) {
		this.doc_lines = doc_lines;
	}



>>>>>>> 7deb84a96367460c984886d0c00aa670b45997f2
	
	
	

}
