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
	


	/********Client************/
	public String requestDocument(DocumentType reason,int qte , int idProd);//almost
	public String cancelRequestDocument(long documentID);//almost
	public String updateRequestDocument(long documentID);//almost
	public List<Document> listDocumentByUser(long userID);//done
	/***********Document line******************************/
	/*public long addLine(Document_line document);//done
	public void updateLine(Document_line newDocument);//done
	public void deleteLine(long documentID);//done
	public List<Document_line> listLine();//done*/

	/********Admin*************/
	public void validateRequestDocument(long documentID);//almost
	public long addDocument(Document document);//done
	public void updateDocument(Document newDocument);//done
	public void deleteDocument(long documentID);//done
	public List<Document> listQuotes();//done
	public List<Document> listBills();//done
	public Document findDocByID(long documentID);//done

	/***business 
	 * @return ****/
	public String calculateBill(long billID);//done
	public String calculateQuote(long quoteID);//done
	public String trackDocumentState(long documentID);//done
	public String deadlineReminder(long documentID);//done
	public void pdfFormatDocument() ;
	public void excelFormatDocument();
	public String sendDocument();
	public void downloadDocument();
	public void printDocument();
	
	public void statisticDocument();

	
}
