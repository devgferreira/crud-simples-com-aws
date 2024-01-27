package com.gabriel.desafioanotaai.apresentation;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import com.gabriel.desafioanotaai.application.interfaces.ICategoryService;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import com.gabriel.desafioanotaai.domain.model.category.Response.CategoryResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final ICategoryService _categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        _categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategoryDTO = _categoryService.createCategory(categoryDTO);
        CategoryResponse categoryResponse = new CategoryResponse(newCategoryDTO);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll(){
        List<CategoryDTO> categories = _categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathParam("id") String id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryUpdate = _categoryService.updateCategory(id, categoryDTO);
        CategoryResponse categoryResponse= new CategoryResponse(categoryUpdate);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteCategory(@PathParam("id") String id){
        _categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Category deletada!!");
    }
}
