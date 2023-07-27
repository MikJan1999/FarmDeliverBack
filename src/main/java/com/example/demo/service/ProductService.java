package com.example.demo.service;


import com.example.demo.Repo.ProductRepo;
import com.example.demo.model.Address;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

@Autowired
ProductRepo productRepo;

    public Product add(@RequestBody Product product){return productRepo.save(product);}
    public List<Product> getAll(){ return  productRepo.findAll();}
    public Optional<Product> getById(Long id){ return  productRepo.findById(id);}
    public void deleteById(Long id) {productRepo.deleteById(id);}



}
