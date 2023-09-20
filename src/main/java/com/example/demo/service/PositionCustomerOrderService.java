package com.example.demo.service;

import com.example.demo.Repo.CartShopRepo;
import com.example.demo.Repo.PositionCustomerOrderRepo;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.model.CartShop;
import com.example.demo.model.PositionCustomerOrder;
import com.example.demo.model.PositionCustomerOrderWithProductNameDTO;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PositionCustomerOrderService {

@Autowired
PositionCustomerOrderRepo positionCustomerOrderRepo;

@Autowired
    CartShopRepo cartShopRepo;
@Autowired
    ProductRepo productRepo;

    public PositionCustomerOrder add(@RequestBody PositionCustomerOrder positionCustomerOrder){return positionCustomerOrderRepo.save(positionCustomerOrder);}
    public List<PositionCustomerOrder> getAll(){ return  positionCustomerOrderRepo.findAll();}
    public Optional<PositionCustomerOrder> getById(Long id){ return  positionCustomerOrderRepo.findById(id);}
    public void deleteById(Long id) {positionCustomerOrderRepo.deleteById(id);}


    public List<PositionCustomerOrder> findByProductId (Long id){
        return positionCustomerOrderRepo.findByProduct_Id(id);
    }
public List<PositionCustomerOrder> findByCustomerOrderId(Long id){
        return positionCustomerOrderRepo.findByCustomerOrder_Id(id);
}

public Optional<CartShop> findCartShopById(Long id){
        return cartShopRepo.findById(id);
}

public Optional<Product> findProductById(Long productId){return productRepo.findById(productId);}

    public List<Object[]> getAllWithProductName() {
        return positionCustomerOrderRepo.getAllPositionCustomerOrdersWithProductName();
    }

    public List<Object[]> getAllWithProductNameByOrderId(Long customer_order_id) {
        return positionCustomerOrderRepo.getAllPositionCustomerOrdersWithProductNameByOrderId(customer_order_id);
    }

    public PositionCustomerOrderWithProductNameDTO getPositionCustomerOrderWithProductName(Long positionId) {
        PositionCustomerOrder positionCustomerOrder = positionCustomerOrderRepo.findById(positionId).orElse(null);

        if (positionCustomerOrder == null) {
            return null; // Obsłuż przypadek, gdy nie znaleziono pozycji zamówienia.
        }

        PositionCustomerOrderWithProductNameDTO dto = new PositionCustomerOrderWithProductNameDTO(positionCustomerOrder);
        return dto;
    }
    public PositionCustomerOrder getPositionCustomerOrderById(Long positionId) {
        return positionCustomerOrderRepo.findById(positionId).orElse(null);
    }


    public List<PositionCustomerOrder> findPCOByCartShopId(Long cartShopId){
        return positionCustomerOrderRepo.findByCartShop_Id(cartShopId);
    }
}
