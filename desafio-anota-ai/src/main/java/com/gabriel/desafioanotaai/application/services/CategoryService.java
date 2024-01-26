package com.gabriel.desafioanotaai.application.services;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.interfaces.ICategoryService;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.repository.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryService implements ICategoryService {

    private final ICategoryRepository _categoryRepository;
    private final ModelMapper _modelMapper;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        _categoryRepository = categoryRepository;
        _modelMapper = modelMapper;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = _modelMapper.map(categoryDTO, Category.class);
        return _modelMapper.map(_categoryRepository.save(category), CategoryDTO.class);
    }
}
