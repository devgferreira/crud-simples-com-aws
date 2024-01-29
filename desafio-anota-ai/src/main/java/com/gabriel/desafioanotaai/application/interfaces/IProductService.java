package com.gabriel.desafioanotaai.application.interfaces;


import com.gabriel.desafioanotaai.application.dtos.ProductDTO;

import java.util.List;

public interface IProductService {
     ProductDTO createProduct(ProductDTO categoryDTO);
     List<ProductDTO> getAll();
     ProductDTO updateProduct(String id, ProductDTO productDTO);
     void deleteProduct(String id);

}
