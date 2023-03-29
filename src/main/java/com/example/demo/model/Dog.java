package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String breed;

    private String description;

    private String image;

    private String avatar;

    private Boolean online;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog customerTestMca = (Dog) o;
        return Objects.equals(id, customerTestMca.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
