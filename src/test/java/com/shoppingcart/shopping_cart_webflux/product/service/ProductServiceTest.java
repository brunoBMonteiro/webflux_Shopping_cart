package com.shoppingcart.shopping_cart_webflux.product.service;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;
import com.shoppingcart.shopping_cart_webflux.product.repository.ProductRepository;
import com.shoppingcart.shopping_cart_webflux.stubs.ProductCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//Unit test
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private final Product product = ProductCreator.createValidProduct();

    @Test
    @DisplayName("findAll, retorna um flux de produto")
    void findAllReturnFluxOfProductWhenSuccessful(){
        Mockito.when(productRepository.findAll())
                .thenReturn(Flux.just(product));

        StepVerifier.create(productService.findAll())
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    @DisplayName("FindById, retorna um Mono com produto, quando existe")
    void findByIdReturnMonoOfProductWhenSuccessful(){
        Mockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Mono.just(product));

        StepVerifier.create(productService.findById(1L))
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    @DisplayName("FindById, retorna um mono erro, quando produto não existe")
    void findByIdReturnMonoErrorWhenEmptyMonoIsReturned(){
        Mockito.when(productRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.empty());

        StepVerifier.create(productService.findById(1L))
                .expectSubscription()
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    @DisplayName("Save, cria um produto quando der sucesso")
    void saveCreateProductWhenSuccessful(){
        Product clientToBeSaved = ProductCreator.createproductToBesaved();

        Mockito.when(productRepository.save(ProductCreator.createproductToBesaved()))
                        .thenReturn(Mono.just(product));

        StepVerifier.create(productService.save(clientToBeSaved))
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    /*
    @Test
    @DisplayName("Delete, remove o produto quando der sucesso")
    void deleteRemoveProductWhenSuccessful(){
        Mockito.when(productRepository.delete(ArgumentMatchers.any(Product.class)))
                        .thenReturn(Mono.empty());

        StepVerifier.create(productService.delete(1L))
                .expectSubscription()
                .verifyComplete();
    }
     */


}
