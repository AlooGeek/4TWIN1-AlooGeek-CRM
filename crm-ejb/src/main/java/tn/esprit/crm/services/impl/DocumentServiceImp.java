package tn.esprit.crm.services.impl;



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


import tn.esprit.crm.dao.IDocumentDao;
import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.DocumentState;
import tn.esprit.crm.entities.DocumentType;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.services.IDocumentService;
import tn.esprit.crm.util.MailSender;

@Stateless
public class DocumentServiceImp implements IDocumentService {
	
	@PersistenceContext
	EntityManager em;
	
	private IDocumentDao documentDao;

	@Override
	public String requestDocument(DocumentType reason, int qte,int idProd) {

		// create doc (command) use it to send mail
		Document commande = new Document();
		commande.setType(DocumentType.command);
		commande.setDate_creation(new Date());
		//notify admin to validate it by mail
	
		String prod = em.createQuery("select p.label from Product p where p.id= :idProd ")
				.setParameter("idProd", idProd)
				.getSingleResult().toString();
		String description = " Produit : "+prod + ", quantité commandé"+qte ; 
		System.out.println(description);

		MailSender mailSender = new MailSender();
		String messageBody = "Dear Stock Supervisor, <br>"
				+ "You have a new  request from the client (client name) :"+". <br>"
				+ "<b>Request details:</b> <br>"
				+ "Request context: " + reason + "<br>"
				+ "Products Description: " + description + "<br>"
				+ "Date: " + commande.getDate_creation() + "<br>"
				+"Please take the time to review it . <br> Cordially";
		try {
			mailSender.sendMessage(
					"smtp.gmail.com",
					"rabeimelek9@gmail.com",
					"yaoming9",
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
		}
		return "Email request sent successfully";
	
	}

	@Override
	public String cancelRequestDocument(long documentID) {
		
		//delete command 
		em.remove(em.find(Document.class, documentID));
		//send mail to inform the admin
		MailSender mailSender = new MailSender();
		String messageBody = "Dear Stock Supervisor, <br>"
				+ "You the client (client name) has cancelled his request "+". <br>"
				+ "<b>cancellation details:</b> <br>"
				+ "Request reference : "+ documentID + "<br>"
				+ "Date: " + new Date() + "<br>";
		try {
			mailSender.sendMessage(
					"smtp.gmail.com",
					"rabeimelek9@gmail.com",
					"yaoming9",
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
					"yaoming9",
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
		return "Command updated successfully";
		
	}

	@Override
	public List<Document> listDocumentByUser(long userID) {
		// TODO Auto-generated method stub
		List<Document> docs =
				em.createQuery("select d from Document d where d.idUser=:  ",Document.class)
				.setParameter("userID",userID).
				getResultList();
		docs = new ArrayList<Document>();
		for(Document doc : docs){
			docs.add(doc);
		}
		return docs;
	}

	@Override
	public void validateRequestDocument(long documentID) {
		Document document = em.find(Document.class,documentID );
		// check the product quantity (stock)
		
		//suggest the available store
		
		 //add document (bill /devis)
		
		Document autoDoc= new Document();
		autoDoc.setDate_creation(new Date());
		//update state to treated (true)

		autoDoc.setState(DocumentState.treated);
		//check type 
		if(document.getType()==DocumentType.command && document.getState()==DocumentState.toBill) {
			
		autoDoc.setType(DocumentType.bill);
		this.calculateBill(autoDoc.getId());
		} else {
		autoDoc.setType(DocumentType.quote);
		this.calculateQuote(autoDoc.getId());}
		//set ligne document 
	
	}

	@Override
	public long addDocument(Document document) {
		//if command state is true auto create document
		em.persist(document);
		return document.getId();
	}

	@Override
	public void updateDocument(Document newDocument) {
		
		Document doc = em.find(Document.class, newDocument.getId());
		em.merge(doc);
		
		
	}

	@Override
	public void deleteDocument(long documentID) {
		em.remove(em.find(Document.class, documentID));
	}

	@Override
	public List<Document> listQuotes() {
		List<Document> quotes = em.createQuery("select d from Document d where d.type= 2 ",Document.class).getResultList();
		List<Document> quotesList = new ArrayList<Document>();
		for(Document quote : quotes){
			quotesList.add(quote);
		}
		return quotesList;
	}

	@Override
	public List<Document> listBills() {
		List<Document> bills = em.createQuery("select d from Document d where d.type= 2 ",Document.class).getResultList();
		List<Document> billsList = new ArrayList<Document>();
		for(Document bill : bills){
			billsList.add(bill);
		}
		return billsList;
	}
	@Override
	public Document findDocByID(long documentID) {
		
		if (em.contains( em.find(Document.class,documentID))== true) {
		Document doc = em.find(Document.class,documentID);
		return doc;}
		else return null;
	}
	

	@Override
	public String calculateBill(long billID) {
		
		//comparer à l id en parametre 
		
		Document document = this.findDocByID(billID);
		//must create method to calculate ttc n ht
		 String ht = em.createQuery("SELECT   (p.unitPrice*d.quantity) "
					+ "FROM Document_line d, Product p , Document doc"
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :billID "
					+ " and doc.type = 1").setParameter("billID", billID)
				 .getSingleResult().toString();
		 float totalht = Float.parseFloat(ht);
		document.setTotal_ht(totalht);
		 
		String ttc= em.createQuery("SELECT   (p.unitPrice*d.quantity*(p.tva/100)+ (p.unitPrice*d.quantity)) "
					+ "FROM Document_line d, Product p , Document doc "
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :billID  "
					+ " and doc.type = 1").setParameter("billID", billID).getSingleResult().toString();
		 float totalttc = Float.parseFloat(ttc);
		 document.setTotal_ttc(totalttc);
		
		String total =  em.createQuery("SELECT   SUM((p.unitPrice*d.quantity*(p.tva/100)) + (p.unitPrice*d.quantity))  as total "
				+ "FROM Document_line d, Product p , Document doc"
				+ " WHERE d.product= p.id\r\n" + 
				"and d.document = doc.id "
				+ " and doc.id= :billID "
				+ " and doc.type = 1").setParameter("billID", billID).getSingleResult().toString();
		 float tot = Float.parseFloat(total);
		 document.setTotal(tot);

		
		
					
		return " Facture" + billID+"Le total =" + tot + ", totalTTC = "+totalttc+", totalHT = "+ totalht;

		
	}
	

	@Override
	public String calculateQuote(long quoteID) {
		Document document = this.findDocByID(quoteID);
		//must create method to calculate ttc n ht
		 String ht = em.createQuery("SELECT   (p.unitPrice*d.quantity) "
					+ "FROM Document_line d, Product p , Document doc"
					+ " WHERE d.product= p.id\r\n" + 
					"and d.document = doc.id "
					+ " and doc.id= :quoteID "
					+ " and doc.type = 0").setParameter("quoteID",quoteID)
				 .getSingleResult().toString();
		 float totalht = Float.parseFloat(ht);
		document.setTotal_ht(totalht);
		 
		String ttc= em.createQuery("SELECT   (p.unitPrice*d.quantity*(p.tva/100) + (p.unitPrice*d.quantity)) "
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
		 return " Facture" + quoteID+"Le total =" + tot + ", totalTTC = "+totalttc+", totalHT = "+ totalht;
		 
		 
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
		return "Your bill has been already treated";
		else if (doc.getState()== DocumentState.notTreated && doc.getType()== DocumentType.bill) 
		return "Your bill is not treated yet";
		else if(doc.getState()==DocumentState.treated && doc.getType()==DocumentType.quote)
		
		return "Your quote has been already treated";
		else if (doc.getState()==DocumentState.notTreated && doc.getType()==DocumentType.quote)
		return "Your quote is not treated yet";
		else return "Your document is"+doc.getState();
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
		}
		else {
			System.out.println("Your bill has been automaticaly canceled");
			doc.setState(DocumentState.canceled);
			return "Your bill has been automaticaly canceled";
		}

		}
	}
	@Override
	public void pdfFormatDocument()  {
		
	}
	    
	        
	       
		
	

	@Override
	public void excelFormatDocument() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String sendDocument() {
		
		MailSender mailSender = new MailSender();
		String messageBody = "Dear Stock Supervisor, <br>"
				+ "You have a new  request from the client (client name) :"+". <br>"
				+ "<b>Request details:</b> <br>"
				+ "Request context: " + "test" + "<br>"
				+ "Products Description: " + "test dec" + "<br>"
				+ "Date: " + new Date() + "<br>"
				+"Please take the time to review it . <br> Cordially";
		try {
			mailSender.sendMessage(
					"smtp.gmail.com",
					"rabeimelek9@gmail.com",
					"yaoming9",
					"587",
					"true",
					"true",
					"rabeimelek9@gmail.com",
					"Your Bill ",
					messageBody
			);
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "Email request sent successfully";
			
	}

	@Override
	public void downloadDocument() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void printDocument() {
		
		 /*PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
	        for (PrintService printService : printServices) {
	            if (printService.getName().trim().equals(printerName)) {
	                return printService;
	            }
	        }
	        return null;*/
		
	}

	@Override
	public void statisticDocument() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
