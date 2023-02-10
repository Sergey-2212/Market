package ru.gb.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.Dto.ProductDto;
import ru.gb.market.converters.ProductConverter;
import ru.gb.market.exceptioms.NotFoundException;
import ru.gb.market.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/")
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(p -> productConverter.entityToDtoConverter(p)).collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
      return productConverter.entityToDtoConverter(
               productService.findProductById(id)
                       .orElseThrow(() -> new NotFoundException(String.format("Product not found by id = %s",id))));
    }


}
