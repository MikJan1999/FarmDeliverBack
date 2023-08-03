package com.example.demo.Repo;

import com.example.demo.model.PositionCustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionCustomerOrderRepo extends JpaRepository<PositionCustomerOrder,Long> {
    List<PositionCustomerOrder> findByProduct_Id(Long productid);
    List<PositionCustomerOrder> findByCustomerOrder_Id(Long customerOrder_id);


}
