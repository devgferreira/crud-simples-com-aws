package com.gabriel.desafioanotaai.domain.repository;

import com.gabriel.desafioanotaai.domain.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
}
