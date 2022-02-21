package uz.webbrain.appgreenshop.service.impl;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 1:25 PM
 */


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.webbrain.appgreenshop.dto.request.CategoryDTO;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.entity.Category;
import uz.webbrain.appgreenshop.repository.CategoryRespository;
import uz.webbrain.appgreenshop.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRespository categoryRespository;

    @Override
    public HttpEntity<?> addCategory(CategoryDTO categoryDTO) {
        Response response = new Response();
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if(categoryDTO.getParentId() != null){
            Category parent = findById(categoryDTO.getParentId());
            if(parent != null){
                category.setParent(parent);
            }
        }
        category = categoryRespository.save(category);
        response.setSuccess(true);
        response.setData(category);
        return ResponseEntity.ok(response);
    }
    @Override
    public HttpEntity<?> findAllPageable(Pageable pageable) {
        Response response = new Response();
        Page<Category> categoryAll = categoryRespository.findAll(pageable);
        List<Category> categoryList = categoryAll.getContent();
        response.setSuccess(true);
        if(categoryList.size() == 0){
            response.setMessage("No categories were found");
        }
        else {
            response.setMessage("Category list");
        }
        response.setDataList(new ArrayList<>(categoryList));
        response.getMap().put("size", categoryAll.getSize());
        response.getMap().put("total_elements", categoryAll.getTotalElements());
        response.getMap().put("total_pages", categoryAll.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @Override
    public HttpEntity<?> findAll() {
        Response response = new Response();
        List<Category> categoryList = categoryRespository.findAll();
        response.setSuccess(true);
        if(categoryList.size() == 0){
            response.setMessage("No categories were found");
        }else {
            response.setDataList(new ArrayList<>(categoryList));
        }
        return ResponseEntity.ok(response);

    }

    @Override
    public HttpEntity<?> editCategory(Long category_id, CategoryDTO categoryDTO) {
        Category category = findById(category_id);
        Response response = new Response();
        if(category == null){
//            throw new NotFoundException("Category Not Found with id: {" + category_id + "}");
            response.setSuccess(false);
            response.setMessage("Category Not Found with id: {" + category_id + "}");
        }else {
            category.setName(categoryDTO.getName());
            Category parent = findById(categoryDTO.getParentId());
            if(parent != null){
                category.setParent(parent);
            }
            category = categoryRespository.save(category);
            response.setData(category);
        }
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @Override
    public HttpEntity<?> deleteCategory(Long category_id) {
        Response response = new Response();
        Category category = findById(category_id);
        if(category == null){
//            throw new NotFoundException("Category Not Found with id: {" + category_id + "}");
            response.setSuccess(false);
            response.setMessage("Category Not Found with id: {" + category_id + "}");
        }else {
            categoryRespository.delete(category);
            response.setSuccess(true);
            response.setMessage("Category {" + category.getName()+"} was successfully deleted");
        }
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }

    @Override
    public Category findById(Long category_id) {
        Optional<Category> categoryOptional = categoryRespository.findById(category_id);
        return categoryOptional.orElse(null);
    }

    @Override
    public HttpEntity<?> findOneById(Long id) {
        Category category = findById(id);
        Response response = new Response();
        if(category == null){
            response.setSuccess(false);
            response.setMessage("Category was not found with id {" + id + "}");
        }else {
            response.setSuccess(true);
            response.setData(category);
        }
        return ResponseEntity.status(response.isSuccess() ? 200 : 404).body(response);
    }


}
