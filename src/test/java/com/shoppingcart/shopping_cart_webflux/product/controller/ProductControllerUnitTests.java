package com.shoppingcart.shopping_cart_webflux.product.controller;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;
import com.shoppingcart.shopping_cart_webflux.product.service.ProductService;
import com.shoppingcart.shopping_cart_webflux.stubs.ProductCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class ProductControllerUnitTests {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private final Product product = ProductCreator.createValidProduct();

    @Test
    @DisplayName("FindAll, retorna um flux de produto")
    void findAllReturnFluxOfProductWhenSuccessful(){
        Mockito.when(productService.findAll())
                        .thenReturn(Flux.just(product));

        StepVerifier.create(productController.listAll())
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    @DisplayName("FindById, retorna um Mono com produto, quando existe")
    void findByIdReturnMonoOfProductWhenSuccessful(){
        Mockito.when(productService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(product));

        StepVerifier.create(productController.findById(1L))
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    @DisplayName("Save, cria um produto quando der sucesso")
    void saveCreateProductWhenSuccessful(){
        Product clientToBeSaved = ProductCreator.createproductToBesaved();

        Mockito.when(productService.save(ProductCreator.createproductToBesaved()))
                .thenReturn(Mono.just(product));

        StepVerifier.create(productController.createProduct(clientToBeSaved))
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    @DisplayName("Delete, remove o produto quando der sucesso")
    void deleteRemoveProductWhenSuccessful(){
        Mockito.when(productService.delete(1L))
                        .thenReturn(Mono.empty());

        StepVerifier.create(productController.deleteProduct(1L))
                .expectSubscription()
                .verifyComplete();
    }


}
