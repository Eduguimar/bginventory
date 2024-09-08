package com.devedu.bginventory.controller.dto;

import com.devedu.bginventory.domain.model.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(Long id, @NotBlank String name, String description) {

    public CategoryDTO(Category category) {
        this(category.getId(), category.getName(), category.getDescription());
    }
}
