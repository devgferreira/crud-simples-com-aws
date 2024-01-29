package com.gabriel.desafioanotaai.application.interfaces;


import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.dtos.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {

     ProductDTO createProduct(ProductDTO productDTO);
     List<ProductDTO> getAll();
     ProductDTO updateProduct(String id, ProductDTO productDTO);
     void deleteProduct(String id);

}
