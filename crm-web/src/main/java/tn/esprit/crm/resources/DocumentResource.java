package tn.esprit.crm.resources;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.crm.dao.IDocumentDao;
import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.DocumentState;
import tn.esprit.crm.entities.DocumentType;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.StoreProduct;
import tn.esprit.crm.services.IDocumentService;

@Path("/Document")
public class DocumentResource {

	//private static final Consumer<? super Document> Document = null;
	public static ArrayList<Document> docs = new ArrayList<Document>();

	@EJB
	IDocumentService documentService;
	@EJB
	IDocumentDao documentDao;
	private final String statusstart= "{\"statusres\":\"";
	private final String statusEnd = "\"}";
	
	@POST
	@Path("/request")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response request( @QueryParam(value="idUser")long idUser) {
		DocumentType type = null ;
	  return Response.status(Status.CREATED).entity(documentService.requestDocument( idUser)).build();
	
	}
	@POST 
	@Path("/cancelrequest")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response cancelRequest(@QueryParam (value="id") long id) {
		 documentService.cancelRequestDocument(id);
		 return  Response.status(Status.OK).entity("Request Cancelled successfully !! ").build();
	}
	
	@POST 
	@Path("/validaterequest")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response validate (@QueryParam(value="idDoc")long idDoc,@QueryParam(value="idProd") long idProd, @QueryParam(value="qte") int qty ) {
	
		int stock = documentDao.getProductQtyById(idProd);
		String res="";
		List<StoreProduct> list = documentDao.listStoreProducts();
		for (StoreProduct sp:list) {
			sp.setQte(stock/2);
		
		res+=" \r\n - Store "+sp.getStores().getName()+" Located at "+	sp.getStores().getAddress() +" and has " + sp.getQte() +" product";
			sp.getQte();
		}
		if ( stock <0) {
			 return  Response.status(Status.OK).entity(statusstart+documentService.validateRequestDocument(idDoc, idProd, qty)+statusEnd ).build();

			
		}else
	
				 return  Response.status(Status.OK).entity(statusstart+documentService.validateRequestDocument(idDoc, idProd, qty) + "   "+ res+statusEnd ).build();

				
	}
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response addDocument() {
		Document doc = new Document();
		doc.setDate_creation(new Date());
		doc.setState(DocumentState.notTreated);
		documentService.addDocument(doc);
			return Response.status(Status.CREATED).entity(doc).build();
	
		}

		
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response updateDocument(Document doc) {
		
			documentService.updateDocument(doc);
			
			return Response.status(Status.OK).entity(doc).build();

		
	}
	
	@PUT
	@Path("/updateRequest")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response updateRequest(@QueryParam(value="id")int id) {
		
			//doc.setState(DocumentState.treated);
			documentService.updateRequestDocument(id);
			return  Response.status(Status.OK).entity(statusstart+"request updated succesfully"+statusEnd).build();

		
	}
	
	
	@DELETE
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response deleteDocument(@QueryParam(value="id")long id) {
		
				documentService.deleteDocument(id);
				Object res = " document" + id+ "deleted succefully";
				return Response.status(Status.OK).entity(statusstart+res+statusEnd).build();
	
	}
		

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response  getDocuments() {
		
		return Response.status(Status.OK).entity(documentDao.listDocs()).build();
			
		}
	
	@GET
	@Path("/getLines")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response  getDocumentLines() {
		return Response.status(Status.OK).entity(documentDao.listLine()).build();
			
		}
	
	@POST 
	@Path("/addline")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response addLine(@QueryParam (value="idDoc")long id, @QueryParam(value="idProd")long idprod) {
		Document_line dl = new Document_line();
		dl.setCreation_date(new Date());
		dl.setQuantity(15);
	 	documentService.addLine(dl, idprod , id);
		return Response.status(Status.ACCEPTED).entity("document line added succesfully").build();
	}
	
	
	@POST
	@Path("/calculateBill")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response  calculateBill(@QueryParam(value="id")long id) {
		
		documentService.calculateBill(id);	
		return Response.status(Status.ACCEPTED).entity("updated succesfully").build();

		
	}
	
	@POST
	@Path("/pdf")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public String pdfFormat() {
		return documentService.pdfCreateDownloadDocument();
	}
	
	private static JsonObject jsonFromString(String jsonObjectStr) {

	    JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
	    JsonObject object = jsonReader.readObject();
	    jsonReader.close();

	    return object;
	}
	@POST
	@Path("/track")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response trackDocument(@QueryParam(value="id")long id) {
		// documentService.trackDocumentState(id);
		String rep=documentService.trackDocumentState(id);		
		return Response.status(Status.OK).entity(statusstart+rep+statusEnd).build();

	}
	
	@POST
	@Path("/reminder")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response reminder(@QueryParam(value="id")long id) {
		
		return Response.status(Status.ACCEPTED).entity(statusstart+documentService.deadlineReminder(id)+statusEnd).build();

	}
	
	
	@POST
	@Path("/stat")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response statisticDocument() {
		
		//return documentService.statisticDocument();
		return Response.status(Status.ACCEPTED).entity(documentService.statisticDocument()).build();

		
	}
	
	@POST
	@Path("/getDoc")
	@Produces(MediaType.APPLICATION_JSON)
		@PermitAll

	public Response getDoc(@QueryParam(value="id")long id) {
		return Response.status(Status.OK).entity(documentService.findDocByID(id)).build();
	}
	
	@POST
	@Path("/getDocDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response getDocDetails(@QueryParam(value="id")long id) {
		return Response.status(Status.OK).entity(documentService.getDocDetails(id)).build();
	}
}
