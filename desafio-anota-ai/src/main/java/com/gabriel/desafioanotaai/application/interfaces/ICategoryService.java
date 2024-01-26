package com.gabriel.desafioanotaai.application.interfaces;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;

import java.util.List;

public interface ICategoryService {
     CategoryDTO createCategory(CategoryDTO categoryDTO);
     List<CategoryDTO> getAll();
     CategoryDTO updateCategory(String id, CategoryDTO categoryDTO);

}
