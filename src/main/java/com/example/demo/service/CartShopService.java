package com.example.demo.service;

import com.example.demo.Repo.CartShopRepo;
import com.example.demo.model.CartShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CartShopService {
    @Autowired
CartShopRepo cartShopRepo;

    public ResponseEntity<String> add(@RequestBody CartShop cartShop) {
        Long userIdExist = cartShop.getUser().getId();
        CartShop cartShopByUserIdExist = cartShopRepo.findByUserId(userIdExist);
        if (cartShopByUserIdExist == null) {
            cartShopRepo.save(cartShop);
            return ResponseEntity.ok().body("Utworzono koszyk");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dany użytkownik już posiada koszyk");
        }
    }

    public List<CartShop> getAll(){ return  cartShopRepo.findAll();}
    public CartShop getById(Long id){ return  cartShopRepo.findById(id).orElseThrow();}
    public void deleteById(Long id) {cartShopRepo.deleteById(id);}


    public CartShop findCartsByUserId(Long userId) {
        return cartShopRepo.findByUserId(userId);
    }
}
