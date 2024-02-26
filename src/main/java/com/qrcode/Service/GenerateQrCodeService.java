package com.qrcode.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.mail.MessagingException;
@Service
public class GenerateQrCodeService {
    @Autowired
	private ImageService imageservice;
    @Autowired 
    private EmailSender emailsender;
	 public String generateQRCOdeImage(String text) throws MessagingException{
		   BitMatrix bitMatrix = null;
		   System.out.println("hi");
			QRCodeWriter qrCodeWriter=new QRCodeWriter();
			try {
				 bitMatrix=qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);
			
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedImage generateQRCOdeImages=MatrixToImageWriter.toBufferedImage(bitMatrix);
			 //save the images
			String fileName="";
			try {
			 fileName=	imageservice.saveImage(generateQRCOdeImages);
			System.out.println(fileName);
		
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return fileName;
		}
}
