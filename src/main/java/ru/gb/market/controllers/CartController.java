package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.Dto.Cart;
import ru.gb.market.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    public Cart getCart() {
        return cartService.getTempCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCartById (@PathVariable Long id) {
        log.debug("ID = " + id);
        cartService.addProduct(id);
    }

    @GetMapping("/changequantity/")
    public void changeItemQuantityById (@RequestParam Long productId, @RequestParam Integer delta) {
         cartService.changeItemQuantityById(productId, delta);
    }


    @DeleteMapping("/delete/item/")
    public void deleteItemById(@RequestParam Long productId) {
        cartService.deleteItem(productId);
    }

    @DeleteMapping("/delete/all/")
    public void deleteAllProductsFromCart () {
        cartService.deleteAllItems();
    }


}
