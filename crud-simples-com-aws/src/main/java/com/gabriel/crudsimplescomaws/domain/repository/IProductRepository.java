package com.gabriel.crudsimplescomaws.domain.repository;

import com.gabriel.crudsimplescomaws.domain.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
}
