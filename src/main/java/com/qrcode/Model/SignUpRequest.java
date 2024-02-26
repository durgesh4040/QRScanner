package com.qrcode.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

  
    @NotBlank
    private String username;

   
    @NotBlank
    private String email;

    
    @NotBlank
    private String password;

   
    @NotBlank
    private String name;
}
