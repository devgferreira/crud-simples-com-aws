package com.gabriel.desafioanotaai.application.services;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.dtos.ProductDTO;
import com.gabriel.desafioanotaai.application.interfaces.ICategoryService;
import com.gabriel.desafioanotaai.application.interfaces.IProductService;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.model.product.Product;
import com.gabriel.desafioanotaai.domain.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final ModelMapper _modelMapper;
    private final IProductRepository _productRepository;

    private final ICategoryService _categoryService;

    public ProductService(ModelMapper modelMapper, IProductRepository productRepository, ICategoryService categoryService) {
        _modelMapper = modelMapper;
        _productRepository = productRepository;
        _categoryService = categoryService;
    }
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        CategoryDTO categoryDTO = _categoryService.getById(productDTO.getCategory().getId()).orElseThrow();
        Product product = _modelMapper.map(productDTO, Product.class);
        product.setCategory(_modelMapper.map(categoryDTO, Category.class));
        return _modelMapper.map(_productRepository.save(product), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAll() {
        return null;
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = _productRepository.findById(id).orElseThrow();
        _categoryService.getById(productDTO.getCategory().getId()).ifPresent(productDTO:: setCategory);

        if(!productDTO.getTitle().isEmpty()){
            product.setTitle(productDTO.getTitle());
        }
        if(!productDTO.getDescription().isEmpty()){
            product.setDescription(productDTO.getTitle());
        }
        if(productDTO.getPrice() == null){
            product.setPrice(productDTO.getPrice());
        }
        return _modelMapper.map(_productRepository.save(product), ProductDTO.class);
    }

    @Override
    public void deleteProduct(String id) {

    }
}
