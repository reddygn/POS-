package com.naveen.ReddyPOS.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmail(String transactionId) {

		final String username = "from@gmail.com";
		final String password = "********";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("naveentest914@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("to@gmail.com, to@gmail.com, to@gmail.com"));
			message.setSubject("Congratulations!");
			message.setText("Your order has been placed." + "" + "" + " TransactionId :: " + transactionId
					+ " Thank you for shopping with us.");

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
