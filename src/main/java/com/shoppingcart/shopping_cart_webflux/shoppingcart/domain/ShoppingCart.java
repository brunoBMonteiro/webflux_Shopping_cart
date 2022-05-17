package com.shoppingcart.shopping_cart_webflux.shoppingcart.domain;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;
import lombok.*;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCart {
    @Id
    private Long id;
    @NotEmpty(message = "A lista não pode estar vazia!")
    private List<Product> products;
}
