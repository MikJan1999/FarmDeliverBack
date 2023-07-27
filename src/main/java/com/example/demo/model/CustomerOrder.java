package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    //czy zamówienie jest w koszyku
    private Boolean isCartShop;
    //czy został sprzedany
    private Boolean isSaled;


    @OneToMany(mappedBy = "customerOrder")
    @JsonManagedReference
    private List<PositionCustomerOrder> positionCustomerOrders;




    @ManyToOne
private Address address;



}
