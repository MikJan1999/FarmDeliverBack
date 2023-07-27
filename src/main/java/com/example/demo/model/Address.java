package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String nameAndSurname;
    private String street;
    private int numberOfHouse;
    private String village;
    private int numberOfPhone;



@OneToMany(mappedBy = "address")
private List<CustomerOrder> customerOrders;

}
