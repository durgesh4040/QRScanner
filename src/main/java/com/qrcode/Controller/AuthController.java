package com.qrcode.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DuplicateKeyException;
import com.qrcode.Model.AuthResponse;
import com.qrcode.Model.LoginRequest;
import com.qrcode.Model.SignUpRequest;
import com.qrcode.Model.User;
import com.qrcode.Security.TokenProvider;
import com.qrcode.Security.UserRole;
import com.qrcode.Service.EmailSender;
import com.qrcode.Service.GenerateQrCodeService;
import com.qrcode.Service.UserService;





@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    
    @Autowired
    private EmailSender emailSender;
    
    @Autowired 
    private GenerateQrCodeService generateQRService;

    @PostMapping("/login")
    public  AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getEmail(), loginRequest.getPassword());
        return  new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp( @RequestBody SignUpRequest signUpRequest) throws Exception {
//        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
//            throw new DuplicatedUserInfoException(String.format("Username %s already been used", signUpRequest.getUsername()));
//        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new Exception(String.format("Email %s already been used", signUpRequest.getEmail()));
        }
            System.out.println("hi");
        userService.saveUser(mapSignUpRequestToUser(signUpRequest));

        String token = authenticateAndGetToken(signUpRequest.getEmail(), signUpRequest.getPassword());
        System.out.println(token);
       String fileName=generateQRService.generateQRCOdeImage(token);
        
       //send email 
       emailSender.sendEmail(signUpRequest.getEmail(), fileName);
        
        


        return new AuthResponse(token);
        
      
    }

    private String authenticateAndGetToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return tokenProvider.generate(authentication);
    }

    private User mapSignUpRequestToUser(SignUpRequest signUpRequest) {
      

    	User user =new User();
    	user.setUsername(signUpRequest.getUsername());
    	user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    	user.setName(signUpRequest.getName());
    	user.setEmail(signUpRequest.getEmail());
    	user.setRole(UserRole.ROLE_USER);
       
        return user;
    }
}
