package com.shoppingcart.shopping_cart_webflux.product.controller;

import com.shoppingcart.shopping_cart_webflux.product.domain.Product;
import com.shoppingcart.shopping_cart_webflux.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("product")
@Tag(name = "Product", description = "Uma api que simula um shopping cart service")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Obter uma lista de produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "encontrou lista", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "204", description = "Tem uma lista mas está vazia", content = @Content),
            @ApiResponse(responseCode = "404", description = "lista de produtos não encontrada", content = @Content)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> listAll(){
        return productService.findAll();
    }

    @Operation(summary = "Obter um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "encontrou o produto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @Operation(summary = "Salva um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "salvou novo produto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Campo do produto em branco", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@Valid @NotNull @NotEmpty @RequestBody Product product){
        return productService.save(product);
    }

    @Operation(summary = "Atualiza informações de um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Informações do produto atualizadas", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(product.withId(id));
    }

    @Operation(summary = "Deleta um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "produto deletado com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable Long id){
        return productService.delete(id);
    }
}
