package com.shoppingcart.shopping_cart_webflux.product.repository;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
