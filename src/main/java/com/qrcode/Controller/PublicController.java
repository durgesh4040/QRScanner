package com.qrcode.Controller;

//import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//import javax.imageio.ImageIO;
import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
//import com.google.zxing.MultiFormatReader;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qrcode.Model.Login;
import com.qrcode.Service.GenerateQrCodeService;
import com.qrcode.Service.ImageService;

import jakarta.mail.MessagingException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
	private ImageService imageservice;
	 
    @Autowired 
    private GenerateQrCodeService generateQRService;
    
	@GetMapping("/")
	public String login(@RequestBody Login login) {
		System.out.println(login);
		return "good working";
	}
	@GetMapping(value="/qrCode" ,produces=MediaType.IMAGE_PNG_VALUE)
  public String generateQRCOdeImage(@RequestBody String text) throws MessagingException{
		return generateQRService.generateQRCOdeImage(text);
		
	}
	@GetMapping("/decoder")
	public String Decoder() {
		   String qrCodeFilePath = "D:\\Demo Authentication\\QRCodeSystem\\src\\main\\resources\\static\\b.png";
	        File qrCodeFile = new File(qrCodeFilePath);
		String res = null;
		try {
			System.out.println(qrCodeFile);
            BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            com.google.zxing.Result result = multiFormatReader.decode(binaryBitmap);
            String qrCodeText = result.getText();
            return qrCodeText;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "Not found";
    }
}
