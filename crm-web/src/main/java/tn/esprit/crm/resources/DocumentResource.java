package tn.esprit.crm.resources;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.crm.dao.IDocumentDao;
import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.DocumentState;
import tn.esprit.crm.entities.DocumentType;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.StoreProduct;
import tn.esprit.crm.services.IDocumentService;
import javax.annotation.security.PermitAll;


@Path("/Document")
public class DocumentResource {

	//private static final Consumer<? super Document> Document = null;
	@EJB
	IDocumentService documentService;
	@EJB
	IDocumentDao documentDao;
	
	@POST
	@Path("/request")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String request() {
	public String request(@QueryParam(value ="qte") int qte , @QueryParam(value="idProd") long idProd, @QueryParam(value="idUser")long idUser) {
		DocumentType type = null ;
	
		return documentService.requestDocument(type.command,qte,idProd, idUser);
	}
	@POST 
	@Path("/cancelrequest")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String cancelRequest(@QueryParam (value="id") long id) {
		return documentService.cancelRequestDocument(id);
	}
	
	@POST 
	@Path("/validaterequest")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String validate (@QueryParam(value="idDoc")long idDoc,@QueryParam(value="idProd") long idProd, @QueryParam(value="qte") int qty ) {
		int stock = documentDao.getProductQtyById(idProd);
		String res="";
		List<StoreProduct> list = documentDao.listStoreProducts();
		for (StoreProduct sp:list) {
			sp.setQte(stock/2);
		
		res+=" \r\n - Store "+sp.getStores().getName()+" Located at "+	sp.getStores().getAddress() +" and has " + sp.getQte() +" product";
			sp.getQte();
		}
		if ( stock <0) {
			
			return documentService.validateRequestDocument(idDoc, idProd, qty) ;
		}else
		return documentService.validateRequestDocument(idDoc, idProd, qty) + "   "+ res;
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public long addDocument() {
		Document doc = new Document();
		doc.setDate_creation(new Date());
		doc.setState(DocumentState.notTreated);
		doc.setType(DocumentType.command);
		return documentService.addDocument(doc);
		
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String getDocuments() {
	
		String res = "";
		java.util.Iterator<Document> it = docDao.listBills().iterator();
	
		 
		while (it.hasNext()) {
	public void updateDocument(Document doc) {
			doc.setState(DocumentState.treated);
			documentService.updateDocument(doc);
		
	}
	
	@PUT
	@Path("/updateRequest")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void updateRequest(@QueryParam(value="id")int id) {
		
			//doc.setState(DocumentState.treated);
			documentService.updateRequestDocument(id);
		
	}
	
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void deleteDocument() {
		documentService.deleteDocument(1);
		System.out.println("Document Deleted Successfully ! ");

	public String deleteDocument(@QueryParam(value="id")long id) {
		
		documentService.deleteDocument(id);
		return"Document"+ id + " Deleted Successfully ! ";}
		
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Object[]  getDocuments() {
		
		return documentDao.listBills().toArray();
			
		}
	
	@GET
	@Path("/getLines")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Object[]  getDocumentLines() {
		
		return documentDao.listLine().toArray();
			
		}
	
	@POST 
	@Path("/addline")
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public String addLine(@QueryParam (value="idDoc")long id, @QueryParam(value="idProd")long idprod) {
		Document_line dl = new Document_line();
		dl.setCreation_date(new Date());
		dl.setQuantity(15);
	 	documentService.addLine(dl, idprod , id);
		return "Document Line added ";
	}
	
	
	@POST
	@Path("/calculateBill")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String calculateBill() {
		return documentService.calculateBill(3);
	}
	
	@POST
	@Path("/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll	
	public void pdfFormat() {
		 //PDFGenerator pd= new PDFGenerator();
		 System.out.println("File created succesfully");
	public String pdfFormat() {
		return documentService.pdfCreateDownloadDocument();
	}
	
	@POST
	@Path("/track")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public void trackDocument() {
	public String trackDocument(@QueryParam(value="id")long id) {
		
		return documentService.trackDocumentState(id);
	}
	
	@POST
	@Path("/reminder")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String reminder() {
	public String reminder(@QueryParam(value="id")long id) {
		
		return documentService.deadlineReminder(id);
	}
	
	
	@POST
	@Path("/stat")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String statisticDocument() {
		
		return documentService.statisticDocument();
	}
}
