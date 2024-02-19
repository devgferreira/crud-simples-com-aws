package com.gabriel.crudsimplescomaws.application.interfaces;

import com.gabriel.crudsimplescomaws.application.dtos.CategoryDTO;
import com.gabriel.crudsimplescomaws.domain.model.category.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
     Optional<Category> getById(String id);

     CategoryDTO createCategory(CategoryDTO categoryDTO);
     List<CategoryDTO> getAll();
     CategoryDTO updateCategory(String id, CategoryDTO categoryDTO);

     void deleteCategory(String id);

}
