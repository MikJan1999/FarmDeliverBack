package com.example.demo.service;

import com.example.demo.Repo.PositionCustomerOrderRepo;
import com.example.demo.model.Address;
import com.example.demo.model.PositionCustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PositionCustomerOrderService {

@Autowired
PositionCustomerOrderRepo positionCustomerOrderRepo;

    public PositionCustomerOrder add(@RequestBody PositionCustomerOrder positionCustomerOrder){return positionCustomerOrderRepo.save(positionCustomerOrder);}
    public List<PositionCustomerOrder> getAll(){ return  positionCustomerOrderRepo.findAll();}
    public Optional<PositionCustomerOrder> getById(Long id){ return  positionCustomerOrderRepo.findById(id);}
    public void deleteById(Long id) {positionCustomerOrderRepo.deleteById(id);}


    public List<PositionCustomerOrder> findByProductId (Long id){
        return positionCustomerOrderRepo.findByProduct_Id(id);
    }






}
