package ru.gb.market.exceptioms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationErrorException extends RuntimeException {


    public ValidationErrorException(List<String> errors) {
        super(errors.stream().collect(Collectors.joining(", ")));
    }
}
