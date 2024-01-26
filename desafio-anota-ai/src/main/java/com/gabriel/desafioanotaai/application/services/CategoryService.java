package com.gabriel.desafioanotaai.application.services;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.interfaces.ICategoryService;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.repository.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CategoryDTO> getAll() {
        List<Category> resultList = _categoryRepository.findAll();
        return resultList.stream()
                .map(category -> _modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(String id, CategoryDTO categoryDTO) {
        Category category = _categoryRepository.findById(id).orElseThrow();
        if (!categoryDTO.getTitle().isEmpty()){
            category.setTitle(categoryDTO.getTitle());
        }
        if (!categoryDTO.getDescription().isEmpty()){
            category.setDescription(categoryDTO.getDescription());
        }
        return _modelMapper.map(_categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = _categoryRepository.findById(id).orElseThrow();
        _categoryRepository.deleteById(id);
    }


}
