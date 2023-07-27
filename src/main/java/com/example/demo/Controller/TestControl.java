package com.example.demo.Controller;

import com.example.demo.model.Product;
import com.example.demo.model.Test;
import com.example.demo.service.TEstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestControl {
@Autowired
private TEstService tEstService;

    @GetMapping("/get") public List<Test> getAll(){ return  tEstService.getAll();}
}
