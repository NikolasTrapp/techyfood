package br.techyfood.productapp.visitors;

import br.techyfood.productapp.enities.ProductEntity;

public interface ProductVisitor {

    void visitBeforeCreate(ProductEntity entity);

    void visitBeforeUpdate(ProductEntity entity);

    void visitBeforeDelete(ProductEntity entity);

    void visitAfterCreate(ProductEntity entity);

    void visitAfterUpdate(ProductEntity entity);

    void visitAfterDelete(ProductEntity entity);

}
