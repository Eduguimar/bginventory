package com.devedu.bginventory.controller.dto;

import com.devedu.bginventory.domain.model.Category;

public record CategoryDTO(Long id, String name, String description) {

    public CategoryDTO(Category category) {
        this(category.getId(), category.getName(), category.getDescription());
    }
}
