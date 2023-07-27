package com.example.demo.model;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class PositionCustomerOrder  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float amount;
    //czy akceptowano dane zamówienie i czy zostanie zrealizowane

    private Boolean isAccepted;

    private Boolean isDelivered;

    @ManyToOne
@JsonBackReference
    private CustomerOrder customerOrder;

@ManyToOne
@JsonBackReference(value="product")
    private Product product;







}
