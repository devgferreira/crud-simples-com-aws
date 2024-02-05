package com.gabriel.desafioanotaai.apresentation;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.dtos.ProductDTO;
import com.gabriel.desafioanotaai.application.interfaces.IProductService;
import com.gabriel.desafioanotaai.domain.model.category.Response.CategoryResponse;
import com.gabriel.desafioanotaai.domain.model.product.response.ProductResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
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
        List<ProductDTO> productDTOS = _productService.getAll();
        return ResponseEntity.ok(productDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathParam("id") String id, @RequestBody ProductDTO productDTO){
        ProductDTO newProductDTO = _productService.updateProduct(id, productDTO);
        ProductResponse productResponse= new ProductResponse(newProductDTO);
        return ResponseEntity.ok().body(productResponse);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteProduct(@PathParam("id") String id){
        _productService.deleteProduct(id);
        return ResponseEntity.ok().body("Product deletada!!");
    }
}
