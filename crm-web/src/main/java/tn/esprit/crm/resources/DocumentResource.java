package tn.esprit.crm.resources;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.DocumentState;
import tn.esprit.crm.entities.DocumentType;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.services.IDocumentService;

@Path("/Document")
public class DocumentResource {

	@EJB
	IDocumentService documentService;
	
	@PUT
	@Path("/request")
	@Produces(MediaType.APPLICATION_JSON)
	public String request() {
		DocumentType type = null ;
	
		return documentService.requestDocument(type.bill,3,1);
	}
	
	@PUT
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public long addDocument() {
		Document doc = new Document();
		doc.setDate_creation(new Date());
		doc.setState(DocumentState.notTreated);
		doc.setType(DocumentType.bill);
		return documentService.addDocument(doc);
		
	}
	
	@POST
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDocuments() {
	
		String res = "";
		java.util.Iterator<Document> it = documentService.listBills().iterator();
	
		 
		while (it.hasNext()) {
	
			res=   it.next().toString();
		
			   
			   
		 return res ;
		}
		return  res ;

	}
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteDocument() {
		documentService.deleteDocument(1);
		System.out.println("Document Deleted Successfully ! ");
		
	}
	
	@POST
	@Path("/calculateBill")
	@Produces(MediaType.APPLICATION_JSON)
	public String calculateBill() {
		return documentService.calculateBill(3);
	}
	
	@POST
	@Path("/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	public void pdfFormat() {
		 //PDFGenerator pd= new PDFGenerator();
		 System.out.println("File created succesfully");
	}
	
	@POST
	@Path("/track")
	@Produces(MediaType.APPLICATION_JSON)
	public void trackDocument() {
		
		 documentService.trackDocumentState(3);
	}
	
	@POST
	@Path("/reminder")
	@Produces(MediaType.APPLICATION_JSON)
	public String reminder() {
		
		return documentService.deadlineReminder(3);
	}
}
