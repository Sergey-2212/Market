package ru.gb.market.Dto;

import lombok.Data;
import ru.gb.market.entities.Product;

import java.math.BigDecimal;
@Data
public class ProductDto {

    private Long id;

    private String title;

    private BigDecimal price;

    public ProductDto(Product product) {
        id = product.getId();
        title = product.getTitle();
        price = product.getPrice();
    }
}
