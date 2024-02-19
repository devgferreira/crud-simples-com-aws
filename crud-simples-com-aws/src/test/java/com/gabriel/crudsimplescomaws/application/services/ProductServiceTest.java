package com.gabriel.crudsimplescomaws.application.services;

import com.gabriel.crudsimplescomaws.application.dtos.MessageDTO;
import com.gabriel.crudsimplescomaws.application.dtos.ProductDTO;
import com.gabriel.crudsimplescomaws.domain.model.category.Category;
import com.gabriel.crudsimplescomaws.domain.model.product.Product;
import com.gabriel.crudsimplescomaws.domain.repository.IProductRepository;
import com.gabriel.crudsimplescomaws.infra.exceptions.CategoryNaoEncontradoException;
import com.gabriel.crudsimplescomaws.infra.exceptions.ProductNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService _productService;

    @Mock
    private IProductRepository _productRepository;

    @Mock
    private AwsSnsService _awsSnsService;
    @Mock
    private CategoryService _categoryService;

    @Mock
    private ModelMapper _modelMapper;

    @Test
    void createProductTest_ComCategoryValida_RetornandoProduct(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");
        ProductDTO productDTO = new ProductDTO("Teste123", "Teste", "Descrição", "123", 12345, category.getId());

        Product product = new Product(productDTO);
        product.setId(productDTO.getId());

        when(_modelMapper.map(productDTO, Product.class)).thenReturn(product);
        when(_categoryService.getById(category.getId())).thenReturn(Optional.of(category));
        when(_productRepository.save(product)).thenReturn(product);
        _awsSnsService.publish(new MessageDTO(product.toString()));
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

    @Test
    void updateProductTest_ComProductIdECategoryIdValido_RetornandoProduct(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");
        ProductDTO productDTO = new ProductDTO("Teste123", "Teste", "Descrição", "123", 12345, category.getId());
        Product product = new Product(productDTO);
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        when(_modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
        when(_productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(_categoryService.getById(category.getId())).thenReturn(Optional.of(category));
        _awsSnsService.publish(new MessageDTO(product.toString()));
        when(_modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
        when(_productRepository.save(product)).thenReturn(product);

        ProductDTO result = _productService.updateProduct(productDTO.getId(), productDTO);
        assertEquals(result, productDTO);

    }

    @Test
    void updateProductTest_ComProductIdInvalido_RetornandoThrowsProductNaoEncontrada(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");
        ProductDTO productDTO = new ProductDTO("Teste123", "Teste", "Descrição", "123", 12345, category.getId());
        Product product = new Product(productDTO);
        product.setId(productDTO.getId());

        when(_productRepository.findById(product.getId())).thenReturn(Optional.empty());

        assertThrows(ProductNaoEncontradoException.class,
                () -> _productService.updateProduct(product.getId(), productDTO));

    }

    @Test
    void updateProductTest_ComCategoryIdInvalido_RetornandoThrowsCategoryNaoEncontrada(){
        Category category = new Category("123123123ASDASDAS", "Teste", "Teste Descrição", "123");
        ProductDTO productDTO = new ProductDTO("Teste123", "Teste", "Descrição", "123", 12345, category.getId());
        Product product = new Product(productDTO);
        product.setId(productDTO.getId());

        when(_productRepository.findById(productDTO.getId())).thenReturn(Optional.of(product));

        when(_categoryService.getById(category.getId())).thenReturn(Optional.empty());

        assertThrows(CategoryNaoEncontradoException.class,
                () -> _productService.updateProduct(productDTO.getId(), productDTO));

    }

    @Test
    void getAllTest(){
        List<Product> products = Arrays.asList(new Product(), new Product());

        List<ProductDTO> productDTOS = products.stream().map(product -> new ProductDTO()).toList();

        when(_productRepository.findAll()).thenReturn(products);
        for (int i = 0; i < products.size(); i++) {
            when(_modelMapper.map(products.get(i), ProductDTO.class)).thenReturn(productDTOS.get(i));
        }

        List<ProductDTO> result = _productService.getAll();

        assertEquals(result, productDTOS);
    }

    @Test
    void deleteProductTest_ComProductIdValida(){
        Product product = new Product();
        product.setId("123");

        when(_productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        _productService.deleteProduct(product.getId());

        verify(_productRepository).delete(product);
    }

    @Test
    void deleteProductTest_ComProductIdInvalida_RetornandoThrowsProductNaoEncontrada(){
        assertThrows(ProductNaoEncontradoException.class,
                () -> _productService.deleteProduct(null));
    }



}