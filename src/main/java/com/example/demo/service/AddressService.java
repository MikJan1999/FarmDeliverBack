//package com.example.demo.service;
//
//import com.example.demo.Repo.AddressRepo;
//
//import com.example.demo.model.Address;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//
//public class AddressService {
//    @Autowired
//    AddressRepo addressRepo;
//
//
//    public Address add(@RequestBody Address address){return addressRepo.save(address);}
//    public List<Address> getAll(){ return  addressRepo.findAll();}
//    public Optional<Address> getById(Long id){ return  addressRepo.findById(id);}
//    public void deleteById(Long id) {addressRepo.deleteById(id);}
//
//
//
//}
