package ru.gb.market.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.Dto.ProductDto;
import ru.gb.market.entities.Product;


@Component
public class ProductConverter {

    public Product dtoToEntityConverter (ProductDto productDto) {

        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());

        return product;
    }

    public ProductDto entityToDtoConverter(Product product) {
        return new ProductDto(product);
    }
}
