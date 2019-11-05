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

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long id;
	private String label;
	private String ImageUrl;
	private float unitPrice;
	private int qte;
	private int tva=18;
	private float newprice;
	private int disponible=1;


	@OneToMany(mappedBy="products",fetch = FetchType.EAGER)
	private Set<StoreProduct> storeproducts;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Discount discount;
	
	@ManyToOne()
	private Categorie category;


	@OneToMany(mappedBy ="productss",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Pack_Product> pack_product;
	
	
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<Document_line> doc_lines;
	


	
	




	public Product(Long id, String label, float unit_price, int qte, int tva,String imgurl,float newprice) {

		
		this.id = id;
		this.label = label;
		this.unitPrice = unit_price;
		this.qte = qte;
		this.tva = tva;
		this.ImageUrl=imgurl;
		this.newprice=newprice;
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

	


	public String getImageUrl() {
		return ImageUrl;
	}



	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}


	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	

	public Categorie getCategory() {
		return category;
	}


	public void setCategory(Categorie category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", label=" + label + ", ImageUrl=" + ImageUrl + ", unitPrice=" + unitPrice
				+ ", qte=" + qte + ", tva=" + tva + ", discount=" + discount + ", category=" + category + "]";
	}


	public float getNewprice() {
		return newprice;
	}


	public void setNewprice(float newprice) {
		this.newprice = newprice;
	}


	public int getDisponible() {
		return disponible;
	}


	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	

	
	

}
