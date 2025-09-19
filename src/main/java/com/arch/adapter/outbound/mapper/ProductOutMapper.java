package com.arch.adapter.outbound.mapper;

import org.mapstruct.Mapper;

import com.arch.adapter.outbound.database.entity.ProductEntity;
import com.arch.domain.Product;

@Mapper(componentModel = "cdi")
public interface ProductOutMapper {

  Product fromEntity(ProductEntity entity);
  ProductEntity toEntity(Product product);

}
