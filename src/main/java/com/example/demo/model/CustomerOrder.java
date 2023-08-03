package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isCartShop;
    //czy został sprzedany
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean isSaled;


    @JsonManagedReference
    @OneToMany(mappedBy = "customerOrder")
    private List<PositionCustomerOrder> positionCustomerOrders;




//    @ManyToOne
//private Address address;



}
