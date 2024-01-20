package com.gabriel.desafioanotaai.domain.repository;

import com.gabriel.desafioanotaai.domain.model.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends MongoRepository<Category, String> {
}
