package com.shoppingcart.shopping_cart_webflux.product.service;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;
import com.shoppingcart.shopping_cart_webflux.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ProductService {
        private final ProductRepository productRepository;

        public Flux<Product> findAll(){
            return productRepository.findAll();
        }

        public Mono<Product> findById(Long id){
            return productRepository.findById(id)
                    .switchIfEmpty(monoResponseStatusNotFoundException());
        }

        public Mono<Product> save(Product product){
            return productRepository.save(product);
        }

        public Mono<Void> update(Product product) {
            return findById(product.getId())
                    .map(clientDb -> product.withId(clientDb.getId()))
                    .flatMap(productRepository::save).thenEmpty(Mono.empty());
        }

        public Mono<Void> delete(Long id){
            return findById(id).flatMap(productRepository::delete);
        }


        public <T> Mono<T> monoResponseStatusNotFoundException(){
            return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
        }

}
