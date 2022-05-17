package com.shoppingcart.shopping_cart_webflux.stubs;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;


public class ProductCreator {
    public static Product createproductToBesaved(){
        return Product.builder()
                .name("Sabonete em barra Glicerinado")
                .value(3.99)
                .amount(3)
                .description("Conteúdo de 85g, Com extrato de Calendula")
                .build();
    }

    public static Product createValidProduct(){
        return Product.builder()
                .name("Sabonete em barra Glicerinado")
                .value(3.99)
                .amount(3)
                .description("Conteúdo de 85g, Com extrato de Calendula")
                .build();
    }

    public static Product createValidUpdatedProduct(){
        return Product.builder()
                .name("Sabonete em barra Glicerinado")
                .value(3.99)
                .amount(3)
                .description("Conteúdo de 85g, Com extrato de Calendula")
                .build();
    }

}
