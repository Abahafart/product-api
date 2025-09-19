package com.arch.adapter.outbound.database;

import java.util.Objects;

import com.arch.adapter.outbound.database.entity.ProductEntity;
import com.arch.adapter.outbound.mapper.ProductOutMapper;
import com.arch.domain.Product;
import com.arch.usecase.ProductsCommandUseCase;
import com.arch.usecase.dto.CreateProduct;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ProductsCommandUseCaseImpl implements ProductsCommandUseCase {

  private final ProductOutMapper mapper;

  public ProductsCommandUseCaseImpl(ProductOutMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public CreateProduct createProduct(Product product) {
    ProductEntity found = ProductEntity.findById(product.getSku());
    ProductEntity entity = mapper.toEntity(product);
    ProductEntity.persist(entity);
    return new CreateProduct(mapper.fromEntity(entity), Objects.isNull(found));
  }

  @Override
  public void deleteProduct(String productId) {
    String query = "delete from products where sku = ?1";
    ProductEntity.delete(query, productId);
  }

  @Override
  public Product updateProductDescription(String productId, String description) {
    ProductEntity found = ProductEntity.findById(productId);
    found.setDescription(description);
    found.persist();
    return mapper.fromEntity(found);
  }
}
