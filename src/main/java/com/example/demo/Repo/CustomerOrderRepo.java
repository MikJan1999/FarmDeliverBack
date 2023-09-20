package com.example.demo.Repo;
import com.example.demo.model.CustomerOrder;
import com.example.demo.model.PositionCustomerOrder;
import com.example.demo.model.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Long > {

   List <CustomerOrder> findByUserId(Long userId);

}
