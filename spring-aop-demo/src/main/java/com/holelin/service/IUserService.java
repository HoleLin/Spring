package com.holelin.service;

import com.holelin.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    boolean login(String username, String password);

    boolean register(User user);

    boolean deleteUser(Long id);

    boolean updateUser(User user);

    List<User> getAllUserInfo();

    User getUserByName(String username);
}
