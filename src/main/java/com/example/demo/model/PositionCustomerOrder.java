package com.example.demo.model;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PositionCustomerOrder  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float amount;
    private float priceAll;

    @JsonBackReference("position_order")
    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;


@ManyToOne
@JsonBackReference("product_position")
@JoinColumn(name = "product_id")
    private Product product;


@ManyToOne
@JsonBackReference("cartShop_position")
private CartShop cartShop;


@Transient //nie mapuje
private String productName;

}
