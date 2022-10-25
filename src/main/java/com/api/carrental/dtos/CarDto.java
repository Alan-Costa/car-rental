package com.api.carrental.dtos;

import com.api.carrental.models.enums.CategoryCar;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarDto {

    @NotBlank
    @Size(max = 130)
    private String name;
    @NotBlank
    @Size(max = 130)
    private String model;
    private CategoryCar categoryCar;

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
