package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.Dto.ProductDto;
import ru.gb.market.converters.ProductConverter;
import ru.gb.market.entities.Product;
import ru.gb.market.services.CartService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@Slf4j
public class CartController {

    private final CartService cartService;
    private final ProductConverter productConverter;

    @GetMapping("/")
    public List<ProductDto> getCart() {
        return cartService.getCart().stream().map(p -> productConverter.entityToDtoConverter(p)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public void addProductToCartById (@PathVariable Long id) {
        log.debug("ID = " + id);
        cartService.addProduct(id);
    }

    @DeleteMapping("/")
    public void deleteProductFromCartById (@RequestParam Long id) {
        cartService.deleteProduct(id);
    }


}
