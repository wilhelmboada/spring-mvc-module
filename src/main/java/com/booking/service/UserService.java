package com.booking.service;

import com.booking.dao.UserRepository;
import com.booking.domain.User;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userDao;

    public UserService(UserRepository userDao) {
        this.userDao = userDao;
    }

    public User createUser(User user) {
        logger.info("Creating new user: {}", user );
        userDao.save(user);
        return user;
    }

    public User getUserById(Long id) {
        logger.info("get user by id: {}", id );

        return userDao.findUserById(id);
    }

    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }
}
