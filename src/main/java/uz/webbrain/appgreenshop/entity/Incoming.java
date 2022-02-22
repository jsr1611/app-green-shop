package uz.webbrain.appgreenshop.entity;

/*
 * project:  app-green-shop
 * author:   Jumanazar Said
 * created:  17/02/2022 12:44 PM
 */

import lombok.*;
import uz.webbrain.appgreenshop.entity.template.AbcEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "incoming")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Incoming extends AbcEntity {

    @Column(name = "price")
    private Double price; // summa

    @Column(name = "sale_price")
    private Double salePrice;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "active")
    private Boolean active;

}
