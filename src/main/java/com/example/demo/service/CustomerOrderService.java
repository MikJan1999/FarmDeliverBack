package com.example.demo.service;

import com.example.demo.Repo.CustomerOrderRepo;
import com.example.demo.model.Address;
import com.example.demo.model.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Service

public class CustomerOrderService {
    @Autowired
    CustomerOrderRepo customerOrderRepo;


    public CustomerOrder add(@RequestBody CustomerOrder customerOrder){return customerOrderRepo.save(customerOrder);}
    public List<CustomerOrder> getAll(){ return  customerOrderRepo.findAll();}
    public Optional<CustomerOrder> getById(Long id){ return  customerOrderRepo.findById(id);}
    public void deleteById(Long id) {customerOrderRepo.deleteById(id);}
}
