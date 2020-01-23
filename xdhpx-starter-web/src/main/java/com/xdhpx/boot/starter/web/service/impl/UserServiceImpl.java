package com.xdhpx.boot.starter.web.service.impl;

import java.util.List;

import com.xdhpx.boot.starter.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xdhpx.boot.starter.web.entity.User;
import com.xdhpx.boot.starter.web.mapper.UserMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User save(User user) {
    	user = userMapper.insert(user);
        return user;
    }
    
    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User getById(String id) {
        return userMapper.getById(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteById(String id) {
        return userMapper.deleteById(id);
    }

}
