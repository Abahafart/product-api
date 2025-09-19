package com.arch.adapter.inbound.rest;

import com.arch.adapter.inbound.rest.configuration.ValidSku;
import com.arch.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.arch.adapter.inbound.rest.dto.ProductInput;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public interface ProductsApi {

  Response createOrUpdateProduct(@ValidSku String productId, @Valid ProductInput productInput);

  Response deleteProduct(@ValidSku String productId);

  Response editProductDescription(@ValidSku String productId, @Valid ProductDescriptionInput
      productDescriptionInput);

  Response getProductById(@ValidSku String productId);

  Response getProducts();

}
