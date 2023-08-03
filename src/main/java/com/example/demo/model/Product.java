package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull("Required product name")
    private String productName ;
    private Double productPrice;
    private String productDescription;
    //Czy produkt jest na sprzedaż czy został wycofany

    @UpdateTimestamp
    @Column(nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }


//@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)


//@JoinTable(name = "product_images",joinColumns = {
//        @JoinColumn(name = "product_id")},
//        inverseJoinColumns = {@JoinColumn(name = "image_id")
//
//})
//private Set<ImageData> productImage;


//@OneToMany(mappedBy = "product")
//@JsonManagedReference(value="product")
//    private List<PositionCustomerOrder> positionCustomerOrders;
//


}
