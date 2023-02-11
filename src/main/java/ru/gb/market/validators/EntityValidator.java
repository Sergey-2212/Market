package ru.gb.market.validators;

import org.springframework.stereotype.Component;
import ru.gb.market.Dto.ProductDto;
import ru.gb.market.exceptioms.ValidationErrorException;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityValidator {

    private List<String> errorList = new ArrayList<>();

    public void checkProductDto (ProductDto productDto) {
        if(productDto.getTitle().isBlank() || productDto.getTitle().isEmpty()) {
            errorList.add(String.format("Title is not valid: " + productDto.getTitle()));
        }

        if(productDto.getPrice().toBigInteger().intValue() <= 0) {
            errorList.add(String.format("Price is not valid: " + productDto.getPrice()));
        }

        if(!errorList.isEmpty()) {
            throw new ValidationErrorException(errorList);
        }
    }
}
