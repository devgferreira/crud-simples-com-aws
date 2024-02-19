package com.gabriel.crudsimplescomaws.apresentation;

import com.gabriel.crudsimplescomaws.application.dtos.ProductDTO;
import com.gabriel.crudsimplescomaws.application.interfaces.IProductService;
import com.gabriel.crudsimplescomaws.domain.model.product.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final IProductService _productService;

    @Autowired
    public ProductController(IProductService productService) {
        _productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO newProductDTO = _productService.createProduct(productDTO);
        ProductResponse productResponse = new ProductResponse(newProductDTO);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(){
        List<ProductDTO> products = _productService.getAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productDTO){
        ProductDTO newProductDTO = _productService.updateProduct(id, productDTO);
        ProductResponse productResponse= new ProductResponse(newProductDTO);
        return ResponseEntity.ok().body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id){
        _productService.deleteProduct(id);
        return ResponseEntity.ok().body("Product deletada!!");
    }
}
