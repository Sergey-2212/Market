package ru.gb.market.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
public class CartItem {

    private Long productId;
    private String title;
    private int pricePerProduct;
    private int totalPrice;
    private int quantity;
}
