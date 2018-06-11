package com.anji.springbootannotationlog.service.impl;

import com.anji.springbootannotationlog.model.User;
import com.anji.springbootannotationlog.repository.UserRepository;
import com.anji.springbootannotationlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * author: chenqiang
 * date: 2018/6/11 16:07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
