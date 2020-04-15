package org.eco.vegalize.services;

import org.eco.vegalize.models.User;
import org.eco.vegalize.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        user = userRepository.save(user);
        return user;
    }
    
}
