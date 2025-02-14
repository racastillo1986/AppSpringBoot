package com.example.service;

import com.example.entity.User;
import com.example.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return (List<User>) this.userRepository.findAll();
    }

    public User findById(Long id){
        return this.userRepository.findById(id).orElse(new User());
    }
}
