package com.cg.customer.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.customer.dao.LoginDao;
import com.cg.customer.entity.Login;
import com.cg.customer.exception.NullCustomerException;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

@Service
public class LoginServiceImpl implements LoginService {

	private final static String ACCOUNT_SID = "AC6e2b4bd6cc73e90139be1e03b9e0256a";
	private final static String AUTH_ID = "62a60b601c66beabc3fed7d7954111df";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}
	
	
	@Autowired
	private LoginDao dao;

	@Override
	public List<Login> getAll() {
		return dao.findAll();
	}

	@Override
	public Login update(Login login) {
		// TODO Auto-generated method stub
		return dao.save(login);
	}

	@Override
	public Login addLogin(Login login) throws NullCustomerException, AddressException, MessagingException, IOException {
		// TODO Auto-generated method stub
		if (checkusername(login.getUserName()))
			throw new NullCustomerException("Username Already present");
		//sendmail(login.getUserName(), login.getPassword(), login.getSecurityans(),login.getCustomer().getEmailId());
		return dao.save(login);
	}

	public boolean checkusername(String username) {
		List<Login> l = dao.findAll();
		for (Login log : l) {
			if (log.getUserName().equalsIgnoreCase(username))
				return true;
		}
		return false;
	}
//	@Override
//	public void resetPass(String username, String pass, String secans) throws NullCustomerException, AddressException, MessagingException, IOException {
//		Login l1 = null;
//		List<Login> l = dao.findAll();
//		for (Login log : l) {
//			if (log.getUserName().equalsIgnoreCase(username)) {
//				l1 = log;
//			}
//			break;
//		}
//		System.out.println(l1);
//		if (l1.getSecurityans().equals(secans)) {
//			l1.setPassword(pass);
//			sendmailresetpass(l1.getPassword(), l1.getCustomer().getEmailId());
//			dao.save(l1);
//		}
//		else
//			throw new NullCustomerException("Enter Valid Security Answer");
//	}

	private void sendmail(String UName, String pass, String secans, String email )
			throws AddressException, MessagingException, IOException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("healthcaresystem12@gmail.com", "hcs@1234");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("healthcaresystem12@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Registration  email");
		msg.setContent("Your have Signedin into CulturoFest with UserName:" + UName + ", and password : " + pass + ", with security answer : "
				+ secans, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Your have Signedin into CulturoFest with UserName:" + UName + ", and password : " + pass + ", with security answer : "
				+ secans, "text/html");
		 Transport.send(msg);

		/*
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * attachPart.attachFile("C:/Users/Isha Pawar/Desktop/New Project/approved.jpg"
		 * ); multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 * Transport.send(msg);
		 */
	}
	
	private void sendmailresetpass( String pass, String email )
			throws AddressException, MessagingException, IOException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("healthcaresystem12@gmail.com", "hcs@1234");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("healthcaresystem12@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Password reset email");
		msg.setContent("Your Password for CulturoFest account is changed. New password is : " + pass, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Your Password for CulturoFest account is changed. New password is : " + pass, "text/html");
		 Transport.send(msg);

		/*
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * attachPart.attachFile("C:/Users/Isha Pawar/Desktop/New Project/approved.jpg"
		 * ); multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 * Transport.send(msg);
		 */
	}
	
//	@Override
//	public Admin getByadminUserName(String adminUserName) {
//		return this.dao.findAll().stream().filter(x -> x.getAdminUserName().equals(adminUserName)).findFirst().get();
//	}
	
	@Override
	public int validateandgenerateotp(String username, String secans) throws Exception {

		List<Login> l = dao.findAll();
		for (Login log : l) {
			if (log.getUserName().equals(username)) {
				if (log.getSecurityans().equals(secans)) {
					int i = sendotp(log.getCustomer().getMobile());
					return i;
				} else
					throw new NullCustomerException("Security answer doesnt match");
			}
		}
		throw new NullCustomerException("username does not exist");

	}
	
	@Override
	public Login changepass(String username,String pass,boolean b) throws NullCustomerException {
		if(b) {            //if otp matches(i.e boolean=true) then execute 
		List<Login> l = dao.findAll();
		for (Login log : l) {
			if (log.getUserName().equals(username)) {
				log.setPassword(pass);
				return dao.save(log);
			}
		}//for
		}//if 
		else throw new NullCustomerException("Enter Valid Otp");
		return null;
	}   
	
	public int sendotp(String number) throws Exception {
		// create instance of Random class
		Random rand = new Random();

		int rand_int1 = rand.nextInt(1000);
		String s = "+91" + number;
		com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(s), new PhoneNumber("+12056711348"),
				"Your otp for password reset is : " + rand_int1).create();

		return rand_int1;
	}

}
