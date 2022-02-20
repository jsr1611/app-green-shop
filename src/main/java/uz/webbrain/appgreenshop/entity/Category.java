package uz.webbrain.appgreenshop.entity;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 12:55 PM
 */


import lombok.*;
import uz.webbrain.appgreenshop.entity.template.AbcEntity;
import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category extends AbcEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;
}
