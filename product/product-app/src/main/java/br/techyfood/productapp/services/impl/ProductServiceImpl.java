package br.techyfood.productapp.services.impl;

import br.techyfood.productapp.enities.ProductEntity;
import br.techyfood.productapp.exceptions.ProductException;
import br.techyfood.productapp.repositories.ProductRepository;
import br.techyfood.productapp.visitors.ProductVisitor;
import br.techyfood.productapp.services.ProductService;
import br.techyfood.productexternal.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static br.techyfood.core.Utils.convert;
import static br.techyfood.core.Utils.format;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductVisitor visitor;

    @Override
    @Transactional(readOnly = true)
    public ProductEntity findById(UUID id) {
        if (isNull(id)) {
            throw new ProductException("Product ID cannot be null.");
        }
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ProductEntity create(ProductDto product) {
        var productEntity = convert(product, ProductEntity.class);
        visitor.visitBeforeCreate(productEntity);
        productEntity = productRepository.save(productEntity);
        visitor.visitAfterCreate(productEntity);
        return productEntity;
    }

    @Override
    @Transactional
    public ProductEntity update(ProductDto product) {
        var productEntity = convert(product, ProductEntity.class);
        visitor.visitBeforeUpdate(productEntity);
        productEntity = productRepository.save(productEntity);
        visitor.visitAfterUpdate(productEntity);
        return productEntity;
    }

    @Override
    @Transactional
    public ProductEntity update(UUID id, ProductDto product) {
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public void delete(ProductDto product) {
        delete(product.getId());
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        var productEntity = findById(id);
        if (isNull(productEntity)) {
            throw new ProductException(format("Any product found with id '{}'.", id));
        }

        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> findAllByRestaurantId(UUID restaurantId) {
        return productRepository.findAllByRestaurant(restaurantId);
    }
}
