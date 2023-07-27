package com.example.demo.service;

import com.example.demo.Repo.GeneralAdvertisementRepo;
import com.example.demo.model.GeneralAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralAdvertisementService {



    @Autowired
    GeneralAdvertisementRepo generalAdvertisementRepo;

    public GeneralAdvertisement add(@RequestBody GeneralAdvertisement generalAdvertisement){return generalAdvertisementRepo.save(generalAdvertisement);}
    public List<GeneralAdvertisement> getAll(){ return  generalAdvertisementRepo.findAll();}
    public Optional<GeneralAdvertisement> getById(Long id){ return  generalAdvertisementRepo.findById(id);}
    public void deleteById(Long id) {
        generalAdvertisementRepo.deleteById(id);}
}
