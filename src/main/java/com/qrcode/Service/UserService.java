package com.qrcode.Service;

import java.util.List;
import java.util.Optional;


import com.qrcode.Model.User;

public interface UserService {

    List<User> getUsers();

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);

    User validateAndGetUserByUsername(String username);

    User saveUser(User user);

    void deleteUser(User user);
}
