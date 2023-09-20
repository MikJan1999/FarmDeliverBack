package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
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

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreateOrder;

    private String description;
    private Float priceOrder;

//address
    private String nameAndSurname;
    private String street;
    private String numberOfHouse;
    private String village;
    private int numberOfPhone;




    @JsonManagedReference("position_order")
    @OneToMany(mappedBy = "customerOrder",cascade = CascadeType.ALL)
    private List<PositionCustomerOrder> positionCustomerOrdersss;

    @JsonBackReference("users")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @PreUpdate
    protected void onUpdate() {
        dataCreateOrder = new Date();
    }


}
