package com.arch.adapter.inbound.rest;

import com.arch.adapter.inbound.rest.configuration.ValidSku;
import com.arch.adapter.inbound.rest.dto.ProductDescriptionInput;
import com.arch.adapter.inbound.rest.dto.ProductInput;
import com.arch.adapter.inbound.rest.mapper.ProductMapper;
import com.arch.domain.Product;
import com.arch.usecase.ProductsCommandUseCase;
import com.arch.usecase.ProductsQueryUseCase;
import com.arch.usecase.dto.CreateProduct;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductsApiController implements  ProductsApi {

  private final ProductsCommandUseCase productsCommandUseCase;
  private final ProductsQueryUseCase productsQueryUseCase;
  private final ProductMapper productMapper;

  public ProductsApiController(ProductsCommandUseCase productsCommandUseCase,
      ProductsQueryUseCase productsQueryUseCase, ProductMapper productMapper) {
    this.productsCommandUseCase = productsCommandUseCase;
    this.productsQueryUseCase = productsQueryUseCase;
    this.productMapper = productMapper;
  }

  @Override
  @PUT
  @Path("/{productId}")
  public Response createOrUpdateProduct(@PathParam("productId") @ValidSku String productId,
      @Valid ProductInput productInput) {
    CreateProduct createProduct = productsCommandUseCase.createProduct(productInput.toProduct(productId));
    if (createProduct.newProduct()) {
      ResponseBuilder responseBuilder = Response.status(Response.Status.CREATED);
      responseBuilder.entity(productMapper.toProductOutput(createProduct.product()));
      return responseBuilder.build();
    }
    ResponseBuilder responseBuilder = Response.ok(productMapper.toProductOutput(createProduct.product()));
    return responseBuilder.build();
  }

  @Override
  @DELETE
  @Path("/{productId}")
  public Response deleteProduct(@PathParam("productId") @ValidSku String productId) {
    productsCommandUseCase.deleteProduct(productId);
    return Response.noContent().build();
  }

  @Override
  @PATCH
  @Path("/{productId}")
  public Response editProductDescription(@PathParam("productId") @ValidSku String productId,
      @Valid ProductDescriptionInput productDescriptionInput) {
    Product product = productsCommandUseCase.updateProductDescription(productId,
        productDescriptionInput.description());
    return Response.ok(productMapper.toProductOutput(product)).build();
  }

  @Override
  @GET
  @Path("/{productId}")
  public Response getProductById(@PathParam("productId") @ValidSku String productId) {
    return Response.ok(productMapper.toProductOutput(productsQueryUseCase.getProductById(productId))).build();
  }

  @Override
  @GET
  public Response getProducts() {
    return Response.ok(productsQueryUseCase.getAllProducts().stream().map(productMapper::toProductOutput).toList()).build();
  }
}
