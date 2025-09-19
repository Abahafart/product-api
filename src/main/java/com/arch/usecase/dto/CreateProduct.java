package com.arch.usecase.dto;

import com.arch.domain.Product;

public record CreateProduct(Product product, boolean newProduct) {

}
