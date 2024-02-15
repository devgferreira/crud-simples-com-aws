package com.gabriel.desafioanotaai.application.services;

import ch.qos.logback.core.net.server.Client;
import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.dtos.MessageDTO;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.repository.ICategoryRepository;
import com.gabriel.desafioanotaai.infra.exceptions.CategoryNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService _categoryService;

    @Mock
    private ICategoryRepository _categoryRepository;
    @Mock
    private AwsSnsService _awsSnsService;

    @Mock
    private ModelMapper _modelMapper;

    @Test
    void createCategoryTest(){
        CategoryDTO categoryDTO = new CategoryDTO("123123123ASDASDAS", "Teste", "Teste Descrição", "123");

        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");

        when(_modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        when(_categoryRepository.save(category)).thenReturn(category);
        _awsSnsService.publish(new MessageDTO(category.toString()));
        when(_modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);

        CategoryDTO result = _categoryService.createCategory(categoryDTO);

        assertEquals(result, categoryDTO);

    }

    @Test
    void getByIdTest(){

        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");

        when(_categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Optional<Category> result = _categoryService.getById(category.getId());

        assertEquals(result.get(), category);
    }

    @Test
    void getByIdTest_ComIdInvalido_RetornandoThrowsCategoryNaoEncontrado(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");

        assertThrows(CategoryNaoEncontradoException.class,
                () -> _categoryService.getById(category.getId()));
    }

    @Test
    void updateCategoryTest(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");

        CategoryDTO categoryDTO = new CategoryDTO("123123123ASDASDAS", "Teste 2", "Teste Descrição 2", "123");

        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());

        when(_categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(_categoryRepository.save(category)).thenReturn(category);
        _awsSnsService.publish(new MessageDTO(category.toString()));
        when(_modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        CategoryDTO result = _categoryService.updateCategory(categoryDTO.getId(),categoryDTO);
        assertEquals(result, categoryDTO);


    }
    @Test
    void updateCategoryTest_ComIdInvalido_RetornandoThrowsCategoryNaoEncontrado(){
        CategoryDTO categoryDTO = new CategoryDTO("123123123ASDASDAS", "Teste 2", "Teste Descrição 2", "123");
        assertThrows(CategoryNaoEncontradoException.class,
                () -> _categoryService.updateCategory(null, categoryDTO));
    }

    @Test
    void deleteCategoryTest(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");

        when(_categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        _categoryService.deleteCategory(category.getId());

        verify(_categoryRepository).delete(category);

    }

    @Test
    void deleteCategoryTest_ComIdInvalido_RetornandoThrowsCategoryNaoEncontrado(){

        assertThrows(CategoryNaoEncontradoException.class,
                () -> _categoryService.deleteCategory(null));
    }

    @Test
    void getAllTest(){
        List<Category> categories = Arrays.asList(new Category(), new Category());

        List<CategoryDTO> categoryDTOs = categories.stream().map(category -> new CategoryDTO()).toList();

        when(_categoryRepository.findAll()).thenReturn(categories);
        for (int i = 0; i < categories.size(); i++) {
            when(_modelMapper.map(categories.get(i), CategoryDTO.class)).thenReturn(categoryDTOs.get(i));
        }

        List<CategoryDTO> result = _categoryService.getAll();

        assertEquals(result, categoryDTOs);


    }

}