package com.example.demo.Controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductControl {

    @Autowired
    ProductService productService;


    @PostMapping("/add")
    public Product add(@RequestBody  Product product) {return productService.add(product);}
    @GetMapping("/get") public List<Product> getAll(){ return  productService.getAll();}
    @GetMapping("/get/{id}") public Optional<Product> getById(@PathVariable("id") Long id){ return productService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) { productService.deleteById(id);}


    @PutMapping("/edit/{id}")
    public @ResponseBody ResponseEntity<Product> editById(@PathVariable("id") Long id, @RequestBody Product productEdit) throws NameNotFoundException {
        Optional<Product> productOptional = this.productService.getById(id);
        if(productOptional.isPresent()){
            Product newProduct = productOptional.get();
            newProduct.setProductName(productEdit.getProductName());
            newProduct.setProductPrice(productEdit.getProductPrice());
            newProduct.setProductDescription(productEdit.getProductDescription());
//            newProduct.setIsForSale(productEdit.getIsForSale());
            this.productService.add(newProduct);
            return ResponseEntity.ok(newProduct);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);
    }



}