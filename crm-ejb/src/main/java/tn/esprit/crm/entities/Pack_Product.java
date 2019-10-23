package tn.esprit.crm.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pack_Product implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int period;
	@ManyToOne
	@JoinColumn(name="PACK_ID",referencedColumnName="id",insertable=false,updatable=false)
	private Pack packs;
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID",referencedColumnName="id_product",insertable=false,updatable=false)
	private Product productss;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public Pack getPacks() {
		return packs;
	}
	public void setPacks(Pack packs) {
		this.packs = packs;
	}
	public Product getProductss() {
		return productss;
	}
	public void setProductss(Product productss) {
		this.productss = productss;
	}	
	
	
	
	
}
