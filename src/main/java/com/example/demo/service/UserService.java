package com.example.demo.service;

import com.example.demo.Repo.UserRepo;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User add(@RequestBody User user){return userRepo.save(user);}
    public List<User> getAll(){ return  userRepo.findAll();}
    public Optional<User> getById(Long id){ return  userRepo.findById(id);}
    public void deleteById(Long id) {userRepo.deleteById(id);}

}
