package com.example.demo.service;
import com.example.demo.Repo.TestRepo;
import com.example.demo.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TEstService {

    @Autowired
    private TestRepo testRepo;
    public List<Test> getAll(){ return  testRepo.findAll();}
}
