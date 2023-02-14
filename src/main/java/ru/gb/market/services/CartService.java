package ru.gb.market.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.Dto.Cart;
import ru.gb.market.entities.Product;
import ru.gb.market.exceptioms.NotFoundException;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
    private Cart tempCart;
    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getTempCart () {
        return tempCart;
    }

    public void addProduct(Long id) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found by id - %s", id)));
        tempCart.add(product);
    }

    public void changeItemQuantityById(Long productId, int delta) {
        tempCart.changeItemQuantityById(productId, delta);
    }
    //TODO реализовать удаление продуктв из корзины
    public void deleteItem(Long productId) {
        tempCart.deleteItemByID(productId);
    }

    public void deleteAllItems() {
        tempCart.deleteAllItems();
    }
}
