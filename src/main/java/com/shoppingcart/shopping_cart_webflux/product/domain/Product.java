package com.shoppingcart.shopping_cart_webflux.product.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("product")
public class Product {
    @Id
    private Long id;
    @NotNull(message = "O nome do produto não pode ser nulo")
    @NotEmpty(message = "O nome do produto não pode estar vazio!")
    private String name;
    @NotEmpty(message = "O valor do produto não pode estar vazio!")
    private double value;
    @NotEmpty(message = "A quantidade do produto não pode estar vazio!")
    private int amount;
}



