package com.arch.usecase;

import java.util.List;

import com.arch.domain.Product;

public interface ProductsQueryUseCase {

  Product getProductById(String id);

  List<? extends Product> getAllProducts();

}
