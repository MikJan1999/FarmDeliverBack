package com.example.demo.service;

import com.example.demo.Repo.CustomerOrderRepo;
import com.example.demo.Repo.PositionCustomerOrderRepo;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.PositionCustomerOrder;
import com.example.demo.model.StatusOrder;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@Service

public class CustomerOrderService {
    @Autowired
    CustomerOrderRepo customerOrderRepo;
    @Autowired
    PositionCustomerOrderRepo positionCustomerOrders;

    public CustomerOrder add(@RequestBody CustomerOrder customerOrder) {
        return customerOrderRepo.save(customerOrder);
    }

    public List<CustomerOrder> getAll() {
        return customerOrderRepo.findAll();
    }

    public Optional<CustomerOrder> getById(Long id) {
        return customerOrderRepo.findById(id);
    }

    public void deleteById(Long id) {
        customerOrderRepo.deleteById(id);
    }


    public CustomerOrder addProductToOrder(PositionCustomerOrder positionCustomerOrder, CustomerOrder customerOrder) {
        customerOrder.getPositionCustomerOrdersss().add(positionCustomerOrder);
        return customerOrder;
    }

    public List<CustomerOrder> findByUserId(Long userId){
        return customerOrderRepo.findByUserId(userId);
    }
}
