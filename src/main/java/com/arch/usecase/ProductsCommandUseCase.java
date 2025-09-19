package com.arch.usecase;

import com.arch.domain.Product;
import com.arch.usecase.dto.CreateProduct;

public interface ProductsCommandUseCase {

  CreateProduct createProduct(Product product);
  void deleteProduct(String productId);

  Product updateProductDescription(String productId, String description);

}
