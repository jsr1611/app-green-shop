package uz.webbrain.appgreenshop.controller;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 1:15 PM
 */

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import uz.webbrain.appgreenshop.dto.request.CategoryDTO;
import uz.webbrain.appgreenshop.service.CategoryService;
import uz.webbrain.appgreenshop.utils.ApiPageable;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public HttpEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @ApiPageable
    @GetMapping
    public HttpEntity<?> getAllAsPageable(@ApiIgnore Pageable pageable){
        return categoryService.findAllPageable(pageable);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/list")
    public HttpEntity<?> getCategories(){
        return categoryService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Long id){
        return categoryService.findOneById(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{category_id}")
    public HttpEntity<?> editCategory(@PathVariable Long category_id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.editCategory(category_id, categoryDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{category_id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long category_id){
        return categoryService.deleteCategory(category_id);
    }
}
