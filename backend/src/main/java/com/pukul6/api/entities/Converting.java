package com.pukul6.api.entities;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity(name = "converting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Converting extends BaseEntity {
    @Column(nullable = false, length = 200)
    private String penulis;

    @Column(nullable = false, length = 1000)
    private String sampul;

    @Column(nullable = false, length = 1000)
    private String sumberGb;

    @Column(nullable = false, length = 1000)
    private String referensi;

    @Column(nullable = true, length = 500)
    private String finansial;

    @Column(nullable = false, length = 200, unique = true)
    private String judul;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category kategori;

    @Column(nullable = false, length = 2000)
    private String news;
}
