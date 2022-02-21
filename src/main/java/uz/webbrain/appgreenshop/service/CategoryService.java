package uz.webbrain.appgreenshop.service;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 1:25 PM
 */

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import uz.webbrain.appgreenshop.dto.request.CategoryDTO;
import uz.webbrain.appgreenshop.entity.Category;

public interface CategoryService {

    HttpEntity<?> addCategory(CategoryDTO categoryDTO);

    HttpEntity<?> findAllPageable(Pageable pageable);

    HttpEntity<?> findAll();

    HttpEntity<?> findOneById(Long id);

    HttpEntity<?> editCategory(Long category_id, CategoryDTO categoryDTO);

    HttpEntity<?> deleteCategory(Long category_id);

    Category findById(Long category_id);

}
