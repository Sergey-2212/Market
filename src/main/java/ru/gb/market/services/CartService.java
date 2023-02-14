package ru.gb.market.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.Dto.Cart;
import ru.gb.market.aspect.LogMethodsArgs;
import ru.gb.market.aspect.Timer;
import ru.gb.market.aspect.TimerAround;
import ru.gb.market.entities.Product;
import ru.gb.market.exceptioms.NotFoundException;

@Service
@RequiredArgsConstructor
@LogMethodsArgs
public class CartService {

    private final ProductService productService;
    private Cart tempCart;
    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }
    @LogMethodsArgs
    public Cart getTempCart () {
        return tempCart;
    }
    @Timer
    @TimerAround
    @LogMethodsArgs
    public void addProduct(Long id) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product not found by id - %s", id)));
        tempCart.add(product);
    }
    @LogMethodsArgs
    public void changeItemQuantityById(Long productId, int delta) {
        tempCart.changeItemQuantityById(productId, delta);
    }

    @LogMethodsArgs
    public void deleteItem(Long productId) {
        tempCart.deleteItemByID(productId);
    }
    @LogMethodsArgs
    public void deleteAllItems() {
        tempCart.deleteAllItems();
    }
}
