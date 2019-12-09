package tn.esprit.crm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.crm.dao.IDocumentDao;
import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.StoreProduct;
@Stateless
public class DocumentDaoImp extends GenericDaoImpl<Document> implements IDocumentDao{
	

	@Override
	public List<Document> listQuotes() {
		List<Document> quotes = em.createQuery("select d from Document d where d.type= 0 ",Document.class).getResultList();
		List<Document> quotesList = new ArrayList<Document>();
		for(Document quote : quotes){
			quotesList.add(quote);
		}
		return quotesList;
	}

	@Override
	public List<Document> listBills() {
		List<Document> bills = em.createQuery("select d from Document d where d.type= 1 ",Document.class).getResultList();
		List<Document> billsList = new ArrayList<Document>();
		for(Document bill : bills){
			billsList.add(bill);
		}
		return billsList;
	}
   @Override
	public List<Document> listDocs() {
		List<Document> bills = em.createQuery("select d from Document d",Document.class).getResultList();
		List<Document> billsList = new ArrayList<Document>();
		for(Document bill : bills){
			billsList.add(bill);
		}
		return billsList;
	}
	@Override
	public List<Document> listDocumentByUser(long userID) {
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
	public Product getProductLabelById(long idProd) {
		Product prod = (Product) em.createQuery("select p.label from Product p where p.id= :idProd ")
				.setParameter("idProd", idProd)
				.getSingleResult();
		return prod;
	}
	@Override
	public int getProductQtyById(long idProd) {
		
		String prod = em.createQuery("select p.qte from Product p where p.id= :idProd ")
				.setParameter("idProd", idProd)
				.getSingleResult().toString();
		int qty = Integer.parseInt(prod);
		return qty;
	}
	@Override
	public List<Document_line> listLine() {
		List<Document_line> lines = em.createQuery("select d from Document_line d  ",Document_line.class).getResultList();
		List<Document_line> linesList = new ArrayList<Document_line>();
		for(Document_line line : lines){
			linesList.add(line);
		}
		return linesList;
	}
	
	@Override
	public String getLineDocByID(long id) {
		
	String lineDoc = em.createQuery("select d.document from Document_line d where d.id = :id ").setParameter("id", id).getSingleResult().toString();
	
	return lineDoc;
	}
	
	@Override
	public String getLineProdByID(long id) {
		
		String lineProd = em.createQuery("select p.label from Product p, Document_line d where d.id = :id and p.id_product = d.product").setParameter("id", id).getSingleResult().toString();
		
		return lineProd;
		}
	
	
	@Override
	public List<StoreProduct> listStoreProducts() {
		List<StoreProduct> StoreProducts = em.createQuery(" select s from StoreProduct s ", StoreProduct.class).getResultList();
		List<StoreProduct> StoreProductsList = new ArrayList<StoreProduct>();
		for(StoreProduct StoreProduct : StoreProducts){
			StoreProductsList.add(StoreProduct);
		}
		return StoreProductsList;
	}

}
