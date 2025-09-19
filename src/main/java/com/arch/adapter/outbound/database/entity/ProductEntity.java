package com.arch.adapter.outbound.database.entity;

import java.math.BigDecimal;

import com.arch.domain.Product;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "products")
public class ProductEntity extends PanacheEntityBase {

  @Id
  private String sku;

  private String name;

  private String description;

  private BigDecimal price;

  public Product toProduct() {
    return new Product(this.name, this.sku, this.description, this.price);
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }
}
