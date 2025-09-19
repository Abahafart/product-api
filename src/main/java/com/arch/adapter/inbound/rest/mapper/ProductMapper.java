package com.arch.adapter.inbound.rest.mapper;

import org.mapstruct.Mapper;

import com.arch.adapter.inbound.rest.dto.ProductOutput;
import com.arch.domain.Product;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

  ProductOutput toProductOutput(Product product);

}
