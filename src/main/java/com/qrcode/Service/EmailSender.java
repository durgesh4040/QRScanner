package com.qrcode.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailSender {
	

	
	private JavaMailSender mailSender;

	public EmailSender(JavaMailSender mailSender) {
	this.mailSender = mailSender;
	}
    //String pathToAttachment="static\\1eeb3aaa-fed2-43cc-b3be-67bed8f259c5.png"; 
    String subject="LoginToken from Allywheel";
    String content = "<p>Hello,</p><p>This is a test email sent from Allywhell for Booking.</p>";

	
	public void sendEmail(String email,String filename) throws MessagingException, UnsupportedEncodingException {
	MimeMessage message = mailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message,true);
               //filename="df1a9025-1acb-468e-8627-ee3ab742140c.png";
	   String pathToAttachment="D:\\Demo Authentication\\QRCodeSystem\\src\\main\\resources\\static\\"+filename;
	   System.out.println(pathToAttachment);
	
	helper.setTo(email);

	helper.setSubject(subject);
	helper.setText(content, true);
	FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
    System.out.println(file);
    helper.addAttachment(filename, file);

	mailSender.send(message);
	}
	
}
