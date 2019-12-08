package tn.esprit.crm.dao;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.Product;
import tn.esprit.crm.entities.StoreProduct;

@Local
public interface IDocumentDao extends IGenericDao<Document>{

	public List<Document> listQuotes();
	public List<Document> listBills();
	public List<Document> listDocs();

	public List<Document> listDocumentByUser(long userID);
	public List<Document_line> listLine();
	public String getLineDocByID(long id);
	Product getProductLabelById(long idProd);
	String getLineProdByID(long id);
	List<StoreProduct> listStoreProducts();
	int getProductQtyById(long idProd);
}
