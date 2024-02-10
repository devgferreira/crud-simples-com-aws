package com.gabriel.desafioanotaai.application.services;

import static org.junit.jupiter.api.Assertions.*;

import com.gabriel.desafioanotaai.application.dtos.ProductDTO;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.model.product.Product;
import com.gabriel.desafioanotaai.domain.repository.ICategoryRepository;
import com.gabriel.desafioanotaai.domain.repository.IProductRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService _productService;

    @Mock
    private IProductRepository _productRepository;

    @Mock
    private CategoryService _categoryService;

    @Mock
    private ModelMapper _modelMapper;

    @Test
    void createProductTest_ComCategoryValida_RetornandoProduct(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");
        ProductDTO productDTO = new ProductDTO("Teste123", "Teste", "Descrição", "123", 12345, category.getId());

        Product product = new Product(productDTO);
        product.setCategory(category);


        when(_modelMapper.map(productDTO, Product.class)).thenReturn(product);
        when(_productRepository.save(product)).thenReturn(product);
        when(_categoryService.getById(category.getId())).thenReturn(Optional.of(category));
        when(_modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);


        ProductDTO result = _productService.createProduct(productDTO);

        assertEquals(result, productDTO);
    }

    @Test
    void createProductTest_ComCategoryInvalida_RetornandoThrowsCategoryNaoEncontrada(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");
        ProductDTO productDTO = new ProductDTO("Teste123", "Teste", "Descrição", "123", 12345, category.getId());
        when(_categoryService.getById(category.getId())).thenReturn(Optional.empty());
        assertThrows(CategoryNaoEncontradoException.class,
                () -> _productService.createProduct(productDTO));
    }


}