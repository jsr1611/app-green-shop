package uz.webbrain.appgreenshop.entity;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 12:52 PM
 */

import lombok.*;
import uz.webbrain.appgreenshop.entity.template.AbcEntity;
import uz.webbrain.appgreenshop.enums.PlantSize;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plant")
public class Plant extends AbcEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // BU RELATED BO'LGAN PLANT ID SI
    @ManyToOne
    @JoinColumn(name = "related_id", nullable = true)
    private Plant related;

    @Enumerated(EnumType.STRING)
    private PlantSize size;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Plant parent;
}
