package ru.gb.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.entities.Product;
import ru.gb.market.exceptioms.NotFoundException;
import ru.gb.market.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private final CartRepository cartRepository;

    public List<Product> getCart () {
        return cartRepository.gellAllProducts();
    }

    public void addProduct(Long id) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found by id - %s", id)));
        cartRepository.addProduct(product);
    }

    public void deleteProduct(Long id) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found by id - %s", id)));
        cartRepository.deleteProduct(product);
    }
}
