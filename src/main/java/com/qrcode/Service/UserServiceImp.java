package com.qrcode.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qrcode.Model.User;
import com.qrcode.Repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	    public List<User> getUsers() {
	        return userRepository.findAll();
	    }

//	    @Override
//	    public Optional<UserModel> getUserByUsername(String username) {
//	        return userRepository.findByUsername(username);
//	    }

	   
	    public Optional<User> getUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    
	    public boolean hasUserWithUsername(String username) {
	        return userRepository.existsByUsername(username);
	    }

	   
	    public boolean hasUserWithEmail(String email) {
	        return userRepository.existsByEmail(email);
	    }

	   
	    public User validateAndGetUserByUsername(String username) {
	        return getUserByEmail(username)
	                .orElseThrow();
	    }

	   
	    public User saveUser(User user) {
	        return userRepository.save(user);
	    }

	    
	    public void deleteUser(User user) {
	        userRepository.delete(user);
	    }

		
		public Optional<User> getUserByUsername(String username) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

	
		
}
