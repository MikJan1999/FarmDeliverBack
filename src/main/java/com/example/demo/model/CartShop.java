package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CartShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "cartShop")
@JsonManagedReference("cartShop_position")
    private List<PositionCustomerOrder> positionCustomerOrderList;

@JsonBackReference("a")
@OneToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PositionCustomerOrder> getPositionCustomerOrderList() {
        return positionCustomerOrderList;
    }

    public void setPositionCustomerOrderList(List<PositionCustomerOrder> positionCustomerOrderList) {
        this.positionCustomerOrderList = positionCustomerOrderList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
