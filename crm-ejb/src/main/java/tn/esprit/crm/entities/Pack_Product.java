package tn.esprit.crm.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pack_Product implements Serializable{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int period;
	@ManyToOne
	@JoinColumn(name="PACK_ID",referencedColumnName="id",insertable=false,updatable=false)
	private Pack packs;
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID",referencedColumnName="id_product",insertable=false,updatable=false)
	private Product products;	
}
