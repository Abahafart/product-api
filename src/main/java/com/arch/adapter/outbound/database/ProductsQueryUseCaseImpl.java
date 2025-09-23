package com.arch.adapter.outbound.database;

import java.util.List;
import java.util.Objects;

import com.arch.adapter.outbound.database.entity.ProductEntity;
import com.arch.domain.ExceptionConstants;
import com.arch.domain.Product;
import com.arch.usecase.ProductsQueryUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;

@ApplicationScoped
public class ProductsQueryUseCaseImpl implements ProductsQueryUseCase {

  @Override
  public Product getProductById(String id) {
    ProductEntity found = (ProductEntity) ProductEntity.findByIdOptional(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format(
        ExceptionConstants.PRODUCT_NOT_FOUND, id)));
    return Objects.isNull(found) ? null : found.toProduct();
  }

  @Override
  public List<? extends Product> getAllProducts() {
    List<ProductEntity> found = ProductEntity.listAll();
    return found.stream().map(ProductEntity::toProduct).toList();
  }
}
