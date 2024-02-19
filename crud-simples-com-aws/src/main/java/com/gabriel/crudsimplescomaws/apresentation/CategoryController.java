package com.gabriel.crudsimplescomaws.apresentation;

import com.gabriel.crudsimplescomaws.application.dtos.CategoryDTO;
import com.gabriel.crudsimplescomaws.application.interfaces.ICategoryService;
import com.gabriel.crudsimplescomaws.domain.model.category.Response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
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
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryUpdate = _categoryService.updateCategory(id, categoryDTO);
        CategoryResponse categoryResponse= new CategoryResponse(categoryUpdate);
        return ResponseEntity.ok().body(categoryResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") String id){
        _categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Category deletada!!");
    }
}
