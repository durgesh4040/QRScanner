package com.qrcode.Model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.qrcode.Security.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "user")
@NoArgsConstructor
public class User {
	
	private String id;

    private String username;
    private String password;
    private String name;
    @Indexed(unique=true)
    private String email;
    private UserRole role;
    private String imageUrl;

  

    private String providerId;

    public User(String username, String password, String name, String email, UserRole role, String imageUrl,   String providerId) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
      
        this.providerId = providerId;
    }

	
	
}
