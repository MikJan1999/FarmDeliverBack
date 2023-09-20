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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class PositionCustomerOrder  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float amount;
    private float priceAll;

//    @Formula("(SELECT p.product_name FROM Product p WHERE p.id = product_id)")
//    @Column
//    private String productNameqqq;

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


    @Transient // Oznacza, że to pole nie jest mapowane do kolumny w bazie danych
    private String productName;

}
