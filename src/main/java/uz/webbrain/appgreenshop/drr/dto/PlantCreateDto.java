package uz.webbrain.appgreenshop.drr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantCreateDto {
    private String name;
    private String description;
    private Long categoryId;
    private Long plantId;

}