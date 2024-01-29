package com.gabriel.desafioanotaai.application.interfaces;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
     Optional<CategoryDTO> getById(String id);

     CategoryDTO createCategory(CategoryDTO categoryDTO);
     List<CategoryDTO> getAll();
     CategoryDTO updateCategory(String id, CategoryDTO categoryDTO);

     void deleteCategory(String id);

}
