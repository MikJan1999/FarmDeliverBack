package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionCustomerOrderWithProductNameDTO {
    private Long positionId;
    private float amount;
    private String pcoDesc;
    private float priceAll;
    private String productName;

    public PositionCustomerOrderWithProductNameDTO(PositionCustomerOrder positionCustomerOrder) {
        this.positionId = positionCustomerOrder.getId();
        this.amount = positionCustomerOrder.getAmount();
        this.priceAll = positionCustomerOrder.getPriceAll();
        this.productName = positionCustomerOrder.getProduct().getProductName();
    }
}
