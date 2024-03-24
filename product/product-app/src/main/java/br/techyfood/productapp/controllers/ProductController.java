package br.techyfood.productapp.controllers;

import br.techyfood.productapp.services.ProductService;
import br.techyfood.productexternal.dtos.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.techyfood.core.Utils.convert;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") @NotNull UUID id) {
        return ResponseEntity.ok(convert(productService.findById(id), ProductDto.class));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(productService.findAll().stream().map(it -> convert(it, ProductDto.class)).toList());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductDto product) {
        return ResponseEntity.ok(convert(productService.create(product), ProductDto.class));
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto product) {
        return ResponseEntity.ok(convert(productService.update(product), ProductDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@RequestBody @Valid ProductDto product, @PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convert(productService.update(id, product), ProductDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") UUID id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody @Valid ProductDto product) {
        productService.delete(product);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
