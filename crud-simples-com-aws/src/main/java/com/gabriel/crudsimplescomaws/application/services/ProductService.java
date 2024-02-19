package com.gabriel.crudsimplescomaws.application.services;

import com.gabriel.crudsimplescomaws.application.dtos.MessageDTO;
import com.gabriel.crudsimplescomaws.application.dtos.ProductDTO;
import com.gabriel.crudsimplescomaws.application.interfaces.ICategoryService;
import com.gabriel.crudsimplescomaws.application.interfaces.IProductService;
import com.gabriel.crudsimplescomaws.domain.enums.ErrorCodes;
import com.gabriel.crudsimplescomaws.domain.model.product.Product;
import com.gabriel.crudsimplescomaws.domain.repository.IProductRepository;
import com.gabriel.crudsimplescomaws.infra.exceptions.CategoryNaoEncontradoException;
import com.gabriel.crudsimplescomaws.infra.exceptions.ExceptionResponse;
import com.gabriel.crudsimplescomaws.infra.exceptions.ProductNaoEncontradoException;
import com.gabriel.crudsimplescomaws.infra.exceptions.constants.ErrorConstants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private final ModelMapper _modelMapper;
    private final IProductRepository _productRepository;

    private final ICategoryService _categoryService;

    private final AwsSnsService _awsSnsService;

    public ProductService(ModelMapper modelMapper, IProductRepository productRepository, ICategoryService categoryService, AwsSnsService awsSnsService) {
        _modelMapper = modelMapper;
        _productRepository = productRepository;
        _categoryService = categoryService;
        _awsSnsService = awsSnsService;
    }
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        _categoryService.getById(productDTO.getCategoryId()).orElseThrow( () ->
                new CategoryNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.CATEGORY_NAO_ENCONTRADO,
                                ErrorConstants.CATEGORY_NAO_ENCONTRADO)));

        Product product = _modelMapper.map(productDTO, Product.class);


        _productRepository.save(product);
        _awsSnsService.publish(new MessageDTO(product.toString()));

        return _modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = _productRepository.findById(id).orElseThrow(
                () -> new ProductNaoEncontradoException(
                        new ExceptionResponse(ErrorCodes.PRODUCT_NAO_ENCONTRADO,
                                ErrorConstants.PRODUCT_NAO_ENCONTRADO)));

        if(productDTO.getCategoryId() != null){
             _categoryService.getById(productDTO.getCategoryId()).orElseThrow( () ->
                     new CategoryNaoEncontradoException(
                             new ExceptionResponse(ErrorCodes.CATEGORY_NAO_ENCONTRADO,
                                     ErrorConstants.CATEGORY_NAO_ENCONTRADO)));
             product.setCategoryId(productDTO.getCategoryId());
        }
        if(!productDTO.getTitle().isEmpty()){
            product.setTitle(productDTO.getTitle());
        }
        if(!productDTO.getDescription().isEmpty()){
            product.setDescription(productDTO.getDescription());
        }
        if(!(productDTO.getPrice() == null)){
            product.setPrice(productDTO.getPrice());
        }

        _productRepository.save(product);
        _awsSnsService.publish(new MessageDTO(product.toString()));

        return _modelMapper.map(product, ProductDTO.class);
    }
    @Override
    public List<ProductDTO> getAll() {
        List<Product> resultList = _productRepository.findAll();
        return resultList.stream()
                .map(product -> _modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(String id) {
        Product product = _productRepository.findById(id).orElseThrow(
                () -> new ProductNaoEncontradoException(
                new ExceptionResponse(ErrorCodes.PRODUCT_NAO_ENCONTRADO,
                        ErrorConstants.PRODUCT_NAO_ENCONTRADO)));
        _productRepository.delete(product);

    }
}
