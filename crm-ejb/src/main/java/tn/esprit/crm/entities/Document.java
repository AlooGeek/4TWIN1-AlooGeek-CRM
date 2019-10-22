package tn.esprit.crm.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Document implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private float total_ht;
	private float total_ttc;
	private float total;
	private Date date_creation;
	private DocumentType type;
	private DocumentState State;
	
	@OneToMany (mappedBy = "document",cascade = CascadeType.ALL)
	private Set<Document_line> doc_lines;
	
	@ManyToOne
	User user;

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(long id, float total_ht, float total_ttc, float total, Date date_creation, DocumentType type,
			DocumentState state, Set<Document_line> doc_lines, User user) {
		super();
		this.id = id;
		this.total_ht = total_ht;
		this.total_ttc = total_ttc;
		this.total = total;
		this.date_creation = date_creation;
		this.type = type;
		State = state;
		this.doc_lines = doc_lines;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getTotal_ht() {
		return total_ht;
	}

	public void setTotal_ht(float total_ht) {
		this.total_ht = total_ht;
	}

	public float getTotal_ttc() {
		return total_ttc;
	}

	public void setTotal_ttc(float total_ttc) {
		this.total_ttc = total_ttc;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}



	public DocumentState getState() {
		return State;
	}

	public void setState(DocumentState state) {
		State = state;
	}

	public Set<Document_line> getDoc_lines() {
		return doc_lines;
	}

	public void setDoc_lines(Set<Document_line> doc_lines) {
		this.doc_lines = doc_lines;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", total_ht=" + total_ht + ", total_ttc=" + total_ttc + ", total=" + total
				+ ", date_creation=" + date_creation + ", type=" + type + ", State=" + State + ", user=" + user + "]";
	}
	
	

	
	
	
	
}
