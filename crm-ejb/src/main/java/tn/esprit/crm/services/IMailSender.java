package tn.esprit.crm.services;


import javax.ejb.Local;
import javax.mail.MessagingException;

@Local
public interface IMailSender {

	public void sendMessage(
			String Host,
			String user,
			String password,
			String port,
			String auth,
			String starttls,
			String recipient,
			String subject,
			String messageBody
	)throws MessagingException ;
}