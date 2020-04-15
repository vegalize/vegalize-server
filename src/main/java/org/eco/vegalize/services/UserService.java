package org.eco.vegalize.services;

import org.eco.vegalize.models.User;
import org.eco.vegalize.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        user = userRepository.save(user);
        return user;
    }

    public Optional<User> findUserById(int id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }
}
