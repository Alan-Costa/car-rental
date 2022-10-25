package com.api.carrental.models;

import com.api.carrental.models.enums.CategoryCar;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 130)
    private String name;
    @Column(nullable = false, length = 130)
    private String model;
    @Column(nullable = false)
    private CategoryCar categoryCar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CategoryCar getCategoryCar() {
        return categoryCar;
    }

    public void setCategoryCar(CategoryCar categoryCar) {
        this.categoryCar = categoryCar;
    }
}