package com.example.demo.Repo;

import com.example.demo.model.CartShop;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartShopRepo extends JpaRepository<CartShop,Long> {
    CartShop findByUserId(Long userid);
}
