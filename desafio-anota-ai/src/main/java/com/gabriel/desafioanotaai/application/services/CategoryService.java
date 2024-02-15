package com.gabriel.desafioanotaai.application.services;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.dtos.MessageDTO;
import com.gabriel.desafioanotaai.application.interfaces.ICategoryService;
import com.gabriel.desafioanotaai.domain.enums.ErrorCodes;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.repository.ICategoryRepository;
import com.gabriel.desafioanotaai.infra.exceptions.CategoryNaoEncontradoException;
import com.gabriel.desafioanotaai.infra.exceptions.ExceptionResponse;
import com.gabriel.desafioanotaai.infra.exceptions.constants.ErrorConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository _categoryRepository;
    private final ModelMapper _modelMapper;
    private final AwsSnsService _awsSnsService;


    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper, AwsSnsService awsSnsService) {
        _categoryRepository = categoryRepository;
        _modelMapper = modelMapper;
        _awsSnsService = awsSnsService;
    }


    @Override
    public Optional<Category> getById(String id) {
        return Optional.ofNullable(_categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.CATEGORY_NAO_ENCONTRADO,
                                ErrorConstants.CATEGORY_NAO_ENCONTRADO))
        ));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = _modelMapper.map(categoryDTO, Category.class);
        _categoryRepository.save(category);
        _awsSnsService.publish(new MessageDTO(category.toString()));
        return _modelMapper.map(category, CategoryDTO.class);
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
        Category category = _categoryRepository.findById(id).orElseThrow( () ->
                new CategoryNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.CATEGORY_NAO_ENCONTRADO,
                                ErrorConstants.CATEGORY_NAO_ENCONTRADO)));

        if (!categoryDTO.getTitle().isEmpty()){
            category.setTitle(categoryDTO.getTitle());
        }
        if (!categoryDTO.getDescription().isEmpty()){
            category.setDescription(categoryDTO.getDescription());
        }
        _categoryRepository.save(category);
        _awsSnsService.publish(new MessageDTO(category.toString()));
        return _modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(String id) {
        Category category = _categoryRepository.findById(id).orElseThrow( () ->
                new CategoryNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.CATEGORY_NAO_ENCONTRADO,
                                ErrorConstants.CATEGORY_NAO_ENCONTRADO))  );
        _categoryRepository.delete(category);
    }


}
