package com.xdhpx.boot.starter.web.service;

import java.util.List;

import com.xdhpx.boot.starter.web.entity.User;

public interface UserService {
	
	User save(User user);
    
    List<User> getAll();

    User getById(String id);

    int update(User user);

    int deleteById(String id);
    
}