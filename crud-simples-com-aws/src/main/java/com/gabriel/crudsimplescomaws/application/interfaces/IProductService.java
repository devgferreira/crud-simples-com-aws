package com.gabriel.crudsimplescomaws.application.interfaces;


import com.gabriel.crudsimplescomaws.application.dtos.ProductDTO;

import java.util.List;

public interface IProductService {

     ProductDTO createProduct(ProductDTO productDTO);
     List<ProductDTO> getAll();
     ProductDTO updateProduct(String id, ProductDTO productDTO);
     void deleteProduct(String id);

}
