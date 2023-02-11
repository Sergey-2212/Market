package ru.gb.market.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.entities.Product;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartRepository {

    private final List<Product> products;

    public void addProduct (Product product) {
        products.add(product);
    }

    public List<Product> gellAllProducts() {
        return products;
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
