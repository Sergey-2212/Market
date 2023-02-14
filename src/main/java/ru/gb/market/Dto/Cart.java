package ru.gb.market.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import ru.gb.market.entities.Product;
import ru.gb.market.exceptioms.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Data
public class Cart {
    private int totalPrice;
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
          totalPrice += item.getTotalPrice();
        }
    }

    public void add (Product product) {
        for (CartItem item : items) {
            if(Objects.equals(item.getProductId(), product.getId())) {
                item.setTotalPrice(item.getTotalPrice() + product.getPrice().intValue());
                if(!Objects.equals(item.getPricePerProduct(), product.getPrice().intValue())) {
                    item.setPricePerProduct(item.getTotalPrice()/(item.getQuantity() + 1));
                }
                item.setQuantity(item.getQuantity() + 1);
                recalculate();
                return;
            }
        }

        items.add(new CartItem(product.getId(),
                product.getTitle(),
                product.getPrice().intValue(),
                product.getPrice().intValue(),
                1));
        recalculate();
    }

    public void changeItemQuantityById (Long productId, Integer delta) {
        for (CartItem item: items) {
            if(item.getProductId().equals(productId)) {
                if(delta == -1 && item.getQuantity() <= 1) {
                    return;
                }
                if(delta == 1 && item.getQuantity() >= 100) {
                    return;
                }
                item.setQuantity(item.getQuantity() + delta);
                item.setTotalPrice(item.getPricePerProduct() * item.getQuantity());
                recalculate();
                return;
            }
        }

        throw new NotFoundException(String.format(
                "Продукт c ID = %s не найден в корзине. Класс - %s. Метод - changeItemQuantityById (Long productId, Integer delta)", productId, this.getClass().getSimpleName()));
    }

    public void deleteItemByID (Long productId) {
        for (CartItem item : items) {
            if(item.getProductId().equals(productId)) {
                items.remove(item);
                recalculate();
                return;
            }
        }

        throw new NotFoundException(String.format("Продукт c ID = %s не найден в корзине. Метод - deleteItemByID", productId));
    }

    public void deleteAllItems() {
        items.clear();
        recalculate();
    }
}
