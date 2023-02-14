package ru.gb.market.repositories;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartRepository {

    //TODO затестить postconstruct
    @PostConstruct
    public void setProducts() {
        products = new ArrayList<>();
    }

    private List<Product> products;



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
