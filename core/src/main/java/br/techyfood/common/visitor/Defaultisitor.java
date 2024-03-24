package br.techyfood.common.visitor;

public interface Defaultisitor <T> {

    void visitBeforeCreate(T entity);

    void visitAfterCreate(T entity);

    void visitBeforeUpdate(T entity);

    void visitAfterUpdate(T entity);

    void visitBeforeDelete(T entity);

    void visitAfterDelete(T entity);
}
