package com.example.demo.Repo;

import com.example.demo.model.PositionCustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PositionCustomerOrderRepo extends JpaRepository<PositionCustomerOrder,Long> {
    List<PositionCustomerOrder> findByProduct_Id(Long productid);
    List<PositionCustomerOrder> findByCustomerOrder_Id(Long customer_order_id);
    List<PositionCustomerOrder>findByCartShop_Id(Long cartShopId);
    @Query("SELECT pco.id, pco.amount, pco.priceAll, p.productName AS productName " +
            "FROM PositionCustomerOrder pco " +
            "JOIN pco.product p")
    List<Object[]> getAllPositionCustomerOrdersWithProductName();


    @Query("SELECT pco.id, pco.amount, pco.priceAll, p.productName AS productName " +
            "FROM PositionCustomerOrder pco " +
            "JOIN pco.product p "
            + "WHERE pco.customerOrder.id = :customer_order_id"
    )
    List<Object[]> getAllPositionCustomerOrdersWithProductNameByOrderId(@PathVariable("customer_order_id") Long customer_order_id);
}
