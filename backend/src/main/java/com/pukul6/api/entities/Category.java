package com.pukul6.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true, length = 200)
    private String name;

    @Column(nullable = false, length = 1500)
    private String description;
}
