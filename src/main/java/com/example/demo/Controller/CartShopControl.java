package com.example.demo.Controller;

import com.example.demo.model.CartShop;
import com.example.demo.model.PositionCustomerOrder;
import com.example.demo.service.CartShopService;
import com.example.demo.service.PositionCustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart_shop")
public class CartShopControl {
@Autowired
  CartShopService  cartShopService;
@Autowired
PositionCustomerOrderService positionCustomerOrderService;

    @PostMapping("/add") public ResponseEntity<String> add(@RequestBody CartShop cartShop){return cartShopService.add(cartShop);}
    @GetMapping("/get") public List<CartShop> getAll(){ return  cartShopService.getAll();}
    @GetMapping("/get/{id}") public CartShop getById(@PathVariable("id") Long id){ return cartShopService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) {cartShopService.deleteById(id);}

    @GetMapping("/find/{userId}")
    public CartShop findCartsByUserId(@PathVariable("userId")Long userId){
        return cartShopService.findCartsByUserId(userId);
    }


}
