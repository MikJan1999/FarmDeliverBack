package com.example.demo.Repo;
import com.example.demo.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Long > {

}
