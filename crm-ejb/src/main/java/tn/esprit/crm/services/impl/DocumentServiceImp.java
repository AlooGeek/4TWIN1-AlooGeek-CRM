package tn.esprit.crm.services.impl;



import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.crm.dao.IDocumentDao;
import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.DocumentState;
import tn.esprit.crm.entities.DocumentType;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.StoreProduct;
import tn.esprit.crm.entities.User;
import tn.esprit.crm.services.IDocumentService;
import tn.esprit.crm.services.IProductService;
import tn.esprit.crm.services.IStoreProductService;
import tn.esprit.crm.util.MailSender;
import tn.esprit.crm.util.PDFGenerator;

@Stateless
public class DocumentServiceImp implements IDocumentService {
	
	@PersistenceContext
	EntityManager em;
	
	private IDocumentDao documentDao;
	private IProductService prodService;


	@Override
	public String requestDocument(DocumentType reason, int qte,long idProd, long idUser) {

		
		// create doc (command) use it to send mail
		
			Document commande = new Document();
			commande.setType(DocumentType.command);
			commande.setDate_creation(new Date());
			commande.setState(DocumentState.notTreated);
			commande.setUser(null);
			
			this.addDocument(commande);
	        //add prod list
			 List<Product> prods = em.createQuery("select d from Product d",Product.class).getResultList();
			  
		 	prods.forEach(p-> {System.out.println();
		 	Document_line dl= new Document_line();
		 	dl.setCreation_date(new Date());
		 	this.addLine(dl, p.getId(), commande.getId());});
		 		
		
			/*MailSender mailSender = new MailSender();
			String messageBody = "Dear Stock Supervisor, <br>"
					+ "You have a new  request "+ commande.getId()+"from the client : . <br>"
					+ "<b>Request details:</b> <br>"
					+ "Request context: " + reason + "<br>"
					+ "Products Description: " + description + "<br>"
					+ "Date: " + commande.getDate_creation() + "<br>"
					+"Please take the time to review it . <br> Cordially";
			try {
				mailSender.sendMessage(
						"smtp.gmail.com",
						"rabeimelek9@gmail.com",
						//change password
						"@Yaoming913",
						"587",
						"true",
						"true",
						"rabeimelek9@gmail.com",
						"New "+reason+" Request",
						messageBody
				);
			}
			catch (MessagingException e) {
				throw new RuntimeException(e);
			}*/
			return "Email request sent successfully";
	
	}

	@Override
	public String cancelRequestDocument(long documentID) {
		
		Document doc = em.find(Document.class, documentID);
		doc.setState(DocumentState.canceled);
		//send mail to inform the admin
		MailSender mailSender = new MailSender();
		String messageBody = "Dear Stock Supervisor, <br>"
				+ "The client (client name) has cancelled his request "+". <br>"
				+ "<b>Cancellation details:</b> <br>"
				+ "Request reference : "+ documentID + "<br>"
				+ "Date: " + new Date() + "<br>";
		try {
			mailSender.sendMessage(
					"smtp.gmail.com",
					"rabeimelek9@gmail.com",
					"@Yaoming913",
					"587",
					"true",
					"true",
					"rabeimelek9@gmail.com",
					"New Cancellation",
					messageBody
			);
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "Request cancelled successfully";
		
	}

	@Override
	public String updateRequestDocument(long documentID) {
		// update the command
		
		Document doc = em.find(Document.class,documentID);
		doc.setDate_creation(new Date());
		doc.setState(DocumentState.treated);
		this.updateDocument(doc);
		//notify the admin by mail
		MailSender mailSender = new MailSender();
		String messageBody = "Dear Stock Supervisor, <br>"
				+ "The client (client name) has updated his request "+". <br>"
				+ "<b>Update  details:</b> <br>"
				+ "Request reference : "+ documentID + "<br>"
				+ "Date: " + new Date() + "<br>";
		try {
			mailSender.sendMessage(
					"smtp.gmail.com",
					"rabeimelek9@gmail.com",
					"@Yaoming913",
					"587",
					"true",
					"true",
					"rabeimelek9@gmail.com",
					"New Command update",
					messageBody
			);
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "Command updated successfully";
		
	}


	@Override
	public String validateRequestDocument(long documentID, long prodID, int qte ) {
		Document document = em.find(Document.class,documentID );
		String res="";
		// check the product quantity (stock) of the prod line
		Product prod = em.find(Product.class, prodID);
		int stock= prod.getQte();
		if(stock < 0) {
			document.setState(DocumentState.canceled);
			res =" Sorry , Your request has been canceled, Product Sold OUT ! ";
			return res ;
		}else {
			
		 //add document (bill)	
		document.setState(DocumentState.validated);
		Document autoDoc= new Document();
		autoDoc.setId_commande(document.getId());
		//get the user id
		//autoDoc.setUser(document.getUser());
		autoDoc.setDate_creation(new Date());
		//update state to treated (true)
		autoDoc.setState(DocumentState.treated);
		//check type 
		if(document.getType()==DocumentType.command && document.getState()==DocumentState.validated) {
			autoDoc.setType(DocumentType.bill);
		
		
		this.addDocument(autoDoc);
		//update document type
		//update product qty 
		//prod.setQte(prod.getQte()-qte);
		//calculate bill
		this.calculateBill(document.getId());
		autoDoc.setTotal(document.getTotal());
		autoDoc.setTotal_ttc(document.getTotal_ttc());
		autoDoc.setTotal_ht(document.getTotal_ht());
		
		} 
		//set ligne document
		res= "The request has been validated successfully you will find this product in the following stores"; 
		return res; 
		}
	
	}

	@Override
	public long addDocument(Document document) {
		//if command state is true auto create document
		em.persist(document);
		return document.getId();
	}

	@Override
	public void updateDocument(Document newDocument) {
		
		em.merge(newDocument);
		
		
	}

	@Override
	public void deleteDocument(long documentID) {
		em.remove(em.find(Document.class, documentID));
	}


	@Override
	public Document findDocByID(long documentID) {
		
		if (em.contains( em.find(Document.class,documentID))== true) {
			Document doc = em.find(Document.class,documentID);
			return doc;}
		
		else return null;
	}
	@Override
	public List getDocDetails(long documentID){
		
		long id = this.findDocByID(documentID).getId();
		System.out.println(id);
		List lines = em.createQuery("select d,p.label as label, p.unitPrice as price , p.tva as tva from Document_line d , Product p where d.document.id = :id and d.product.id=p.id ")
				.setParameter("id", id).getResultList();
		List linesList = new ArrayList();
		for(Object line : lines){
			linesList.add(line);
		}
		System.out.println(linesList);
		return linesList;
	}
	

	@Override
	public void calculateBill(long billID) {
		
		
		Document document = this.findDocByID(billID);

		 String ht = em.createQuery("SELECT   sum(p.unitPrice*d.quantity) "
					+ "FROM Document_line d, Product p , Document doc"
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :billID and doc.type = 2").setParameter("billID", billID)
				 .getSingleResult().toString();
		 float totalht = Float.parseFloat(ht);
		document.setTotal_ht(totalht);

		 
		String ttc= em.createQuery("SELECT   sum(p.unitPrice*d.quantity*(p.tva/100)+ (p.unitPrice*d.quantity)) "
					+ "FROM Document_line d, Product p , Document doc "
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :billID and doc.type = 2").setParameter("billID", billID).getSingleResult().toString();
		 float totalttc = Float.parseFloat(ttc);
		 document.setTotal_ttc(totalttc);

		
		String total =  em.createQuery("SELECT   SUM((p.unitPrice*d.quantity*(p.tva/100)) + (p.unitPrice*d.quantity))  as total "
				+ "FROM Document_line d, Product p , Document doc"
				+ " WHERE d.product= p.id\r\n" + 
				"and d.document = doc.id "
				+ " and doc.id= :billID and doc.type = 2").setParameter("billID", billID).getSingleResult().toString();
		 float tot = Float.parseFloat(total);
		 document.setTotal(tot);
		
	}
	

	@Override
	public void calculateQuote(long quoteID) {
		Document document = this.findDocByID(quoteID);
		//must create method to calculate ttc n ht
		 String ht = em.createQuery("SELECT   sum(p.unitPrice*d.quantity) "
					+ "FROM Document_line d, Product p , Document doc"
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :quoteID "
					+ " and doc.type = 0").setParameter("quoteID",quoteID)
				 .getSingleResult().toString();
		 float totalht = Float.parseFloat(ht);
		document.setTotal_ht(totalht);
		 
		String ttc= em.createQuery("SELECT   sum(p.unitPrice*d.quantity*(p.tva/100) + (p.unitPrice*d.quantity)) "
					+ "FROM Document_line d, Product p , Document doc "
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :quoteID  "
					+ " and doc.type = 0").setParameter("quoteID", quoteID).getSingleResult().toString();
		 float totalttc = Float.parseFloat(ttc);
		 document.setTotal_ttc(totalttc);
		
		String total =  em.createQuery("SELECT   SUM((p.unitPrice*d.quantity*(p.tva/100)) + (p.unitPrice*d.quantity))  as total "
				+ "FROM Document_line d, Product p , Document doc"
				+ " WHERE d.product= p.id\r\n" + 
				"and d.document = doc.id "
				+ " and doc.id= :quoteID "
				+ " and doc.type = 0").setParameter("quoteID", quoteID).getSingleResult().toString();
		 float tot = Float.parseFloat(total);
		 document.setTotal(tot);		 
		 
	}

	@Override
	public String trackDocumentState(long documentID) {
		//must test the other states
		//must control verify is the doc exists

		Document doc = findDocByID(documentID);
		if (doc.equals(null))
			return "Document does not exist";
		else {
		if( doc.getState()== DocumentState.treated && doc.getType()== DocumentType.bill)
		return "Your bill has been already treated.";
		else if (doc.getState()== DocumentState.notTreated && doc.getType()== DocumentType.bill) 
		return "Your bill is not treated yet.";
		else if( doc.getState()== DocumentState.payed && doc.getType()== DocumentType.bill)
		return "Your bill has been already payed.";
		else if (doc.getState()== DocumentState.notPayed && doc.getType()== DocumentType.bill) 
		return "Your bill is not payed yet.";
		else if(doc.getState()==DocumentState.treated && doc.getType()==DocumentType.quote)
		return "Your quote has been already treated.";
		else if (doc.getState()==DocumentState.notTreated && doc.getType()==DocumentType.quote)
		return "Your quote is not treated yet.";
		else if( doc.getState()== DocumentState.validated && doc.getType()== DocumentType.command)
		return "Your command has been already validated you are soon getting your bill.";
		else if (doc.getState()== DocumentState.canceled && doc.getType()== DocumentType.command) 
		return "We are sorry , your command has been cancelled .";
		
		else return "Your"+ doc.getType()+" is"+doc.getState();
		}
	}

	@Override
	public String deadlineReminder(long documentID) {
		Document doc = findDocByID(documentID);
		if(doc.equals(null)) return "Document does not exist";
		else {
		Calendar c = Calendar.getInstance(); 
		Date currentDate = new Date();
		Date deadline = doc.getDate_creation();
		Date createdAt = doc.getDate_creation();

		// bill will be automatically rejected after 3 days without being validated
		c.setTime(deadline); 
		c.add(Calendar.DATE, 3);
		deadline = c.getTime();
		System.out.println(currentDate.getDate()+"  " + deadline.getDate() + "   "+ createdAt.getDate());
		int reminding = Math.abs( currentDate.getDate()- deadline.getDate());
		
		if ( deadline.getDate() >= currentDate.getDate()  ) {
			if ( doc.getState()== DocumentState.notTreated) {
				return "Your bill is not treated and it reminds today and  "+ reminding +" days until the deadline";
			}else {
				return "Your bill is treated and it reminds today and  "+ reminding +" days until the deadline";
			}
			//test other possibility
		}
		else {
			System.out.println("Your bill has been automaticaly canceled");
			doc.setState(DocumentState.canceled);
			return "Your bill has been automaticaly canceled";
		}

		}
	}
	@Override
	public String pdfCreateDownloadDocument()  {
		PDFGenerator pdf= new PDFGenerator();
		pdf.createPDF();
		return "PDF File created Successfully";
	}
	   


	@Override
	public String statisticDocument() {
		
		//Stat by total facture lezem l id du client
		String nrml= em.createQuery("SELECT max(doc.total) " + 
				" FROM  Document doc  \r\n" + 
				" WHERE  doc.total <100").getSingleResult().toString();
		//float normal = Float.parseFloat(nrml);
		String avg= em.createQuery("SELECT max(doc.total) \r\n" + 
				" FROM  Document doc \r\n" + 
				" WHERE  doc.total >100 and  doc.total < 1000 ").getSingleResult().toString();
		//float average = Float.parseFloat(avg);
		
		String hg= em.createQuery("SELECT max(doc.total) \r\n" + 
				" FROM  Document doc \r\n" + 
				" WHERE  doc.total >1000").getSingleResult().toString();
		//float high = Float.parseFloat(hg);
		
		//stat by age
		
		return "normal "+ nrml + " Average "+avg + " High "+hg;
		
	}

	@Override
	public long addLine(Document_line docLine, long idProd, long idDoc) {
		Document doc = em.find(Document.class, idDoc);
		Product prod = em.find(Product.class, idProd); 
		docLine.setProduct(prod);
		docLine.setDocument(doc);
		em.persist(docLine);
			
		return docLine.getId();
	}

	@Override
	public void updateLine(Document_line newDocLine) {
		em.merge(newDocLine);
		
	}

	@Override
	public void deleteLine(long docLineID) {
		
		em.remove(em.find(Document_line.class,docLineID));
	}

	public void applyReduction (long idDoc) {
		
		Document bill = em.find(Document.class, idDoc);
		String line =  em.createQuery("select l.product from Document_line l where document= : idDoc")
				.setParameter("idDoc", idDoc)
				.getSingleResult().toString();
		long idProd = Long.parseLong(line);
		Product prod = em.find(Product.class, idProd);
		if (prod.getDiscount()!=null)//
		bill.setTotal(bill.getTotal());
	}

	
	
	
}
