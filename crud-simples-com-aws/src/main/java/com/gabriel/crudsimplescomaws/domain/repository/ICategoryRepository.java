package com.gabriel.crudsimplescomaws.domain.repository;

import com.gabriel.crudsimplescomaws.domain.model.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends MongoRepository<Category, String> {
}
