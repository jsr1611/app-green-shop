package uz.webbrain.appgreenshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.webbrain.appgreenshop.dto.request.PlantCreateDto;
import uz.webbrain.appgreenshop.dto.response.Response;
import uz.webbrain.appgreenshop.entity.Category;
import uz.webbrain.appgreenshop.entity.Plant;
import uz.webbrain.appgreenshop.exception.PlantNotFoundException;
import uz.webbrain.appgreenshop.repository.PlantRepository;
import uz.webbrain.appgreenshop.service.CategoryService;
import uz.webbrain.appgreenshop.service.PlantService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlantServiceImpl implements PlantService {
    private final PlantRepository plantRepository;
    private final CategoryService categoryService;

    @Override
    public Plant save(PlantCreateDto dto) {
        Plant parentPlant = findById(dto.getPlantId());
        Plant plant = new Plant();
        plant.setName(dto.getName());
        plant.setDescription(dto.getDescription());
        Category category = categoryService.findById(dto.getCategoryId());
        plant.setCategory(category);
        plant.setRelated(parentPlant);
        return plantRepository.save(plant);
    }

    @Override
    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    @Override
    public Response findAllPageable(Integer page, Integer size, Sort sort) {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("createdAt"));
        Page<Plant> plantRepositoryAll = plantRepository.findAll(pageable);
        List<Plant> plantList = plantRepositoryAll.getContent();
        Response response = new Response(true, "Plant List", plantList);
        response.getMap().put("size", plantRepositoryAll.getSize());
        response.getMap().put("total_elements", plantRepositoryAll.getTotalElements());
        response.getMap().put("total_pages", plantRepositoryAll.getTotalPages());
        return response;
    }

    @Override
    public Plant findById(Long id) {
        Plant plant = null;
        Optional<Plant> optionalPlant = plantRepository.findById(id);
        if (optionalPlant.isPresent())
            plant = optionalPlant.get();
        return plant;
    }

    @Override
    public Plant update(Long id, PlantCreateDto dto) {
        Plant plant = findById(id);
        if (plant == null)
            throw new PlantNotFoundException("Plant id{" + id + "} not found");
        Plant parentPlant = findById(dto.getPlantId());
        plant.setName(dto.getName());
        plant.setDescription(dto.getDescription());
        Category category = categoryService.findById(dto.getCategoryId());
        plant.setCategory(category);
        plant.setRelated(parentPlant);
        return plantRepository.save(plant);
    }

    @Override
    public Response delete(Long id) {
        Plant plant = findById(id);
        if (plant == null)
            throw new PlantNotFoundException("Plant id{" + id + "} not found");
        plantRepository.delete(plant);
        return new Response("Successfully deleted", plant);
    }
}
