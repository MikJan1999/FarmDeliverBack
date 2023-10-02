package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
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
