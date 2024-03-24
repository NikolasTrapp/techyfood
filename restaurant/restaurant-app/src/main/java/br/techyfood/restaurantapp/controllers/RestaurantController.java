package br.techyfood.restaurantapp.controllers;

import br.techyfood.productexternal.dtos.ProductDto;
import br.techyfood.productexternal.stubs.findproductsbyempresa.FindProductsByEmpresaInput;
import br.techyfood.productexternal.stubs.findproductsbyempresa.FindProductsByEmpresaStub;
import br.techyfood.restaurantapp.services.RestaurantService;
import br.techyfood.restaurantexternal.dtos.RestaurantDto;
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
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final FindProductsByEmpresaStub findProductsByEmpresaStub;

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable(name = "id") @NotNull UUID id) {
        return ResponseEntity.ok(convert(restaurantService.findById(id), RestaurantDto.class));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll().stream().map(it -> convert(it, RestaurantDto.class)).toList());
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody @Valid RestaurantDto restaurant) {
        return ResponseEntity.ok(convert(restaurantService.create(restaurant), RestaurantDto.class));
    }

    @PutMapping
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody @Valid RestaurantDto restaurant) {
        return ResponseEntity.ok(convert(restaurantService.update(restaurant), RestaurantDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurantById(@RequestBody @Valid RestaurantDto restaurant, @PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(convert(restaurantService.update(id, restaurant), RestaurantDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable(name = "id") UUID id) {
        restaurantService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRestaurant(@RequestBody @Valid RestaurantDto restaurant) {
        restaurantService.delete(restaurant);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/list-products/{id}")
    public ResponseEntity<List<ProductDto>> findAllProductsByEmpresaId(@PathVariable(name = "id") UUID id) {
        var input = FindProductsByEmpresaInput.builder().empresaId(id).build();
        return ResponseEntity.ok(findProductsByEmpresaStub.findProductsByEmpresa(input).getProducts());
    }
}
