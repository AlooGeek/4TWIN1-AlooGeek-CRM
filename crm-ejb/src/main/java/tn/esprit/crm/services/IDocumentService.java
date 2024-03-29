package tn.esprit.crm.services;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.crm.entities.Document;
import tn.esprit.crm.entities.DocumentType;
import tn.esprit.crm.entities.Document_line;
import tn.esprit.crm.entities.Product;
@Local
public interface IDocumentService {
	


	/********Client************/ //CRUD not treat 
	public String requestDocument( long idUser);//done
	public String cancelRequestDocument(long documentID);//done
	public String updateRequestDocument(long documentID);//done
	/***********Document line******************************/
	public long addLine(Document_line document,long idProd, long idDoc);//done
	public void updateLine(Document_line newDocument);//done
	public void deleteLine(long documentID);//done
	/********Admin
	 * @return *************/
	public String validateRequestDocument(long documentID,long prodID, int qte );//almost
	public long addDocument(Document document);//done
	public void updateDocument(Document newDocument);//done
	public void deleteDocument(long documentID);//done
	public Document findDocByID(long documentID);//done
	public List<Document_line> getDocDetails(long documentID);//done

	/***business 
	 * @return ****/
	public void calculateBill(long billID);//done
	public void calculateQuote(long quoteID);//done
	public String trackDocumentState(long documentID);//done
	public String deadlineReminder(long documentID);//done
	public String pdfCreateDownloadDocument() ;
	
	public String statisticDocument();
	String requestDocument(DocumentType reason, int qte, long idProd, long idUser);

	
}
