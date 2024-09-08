package com.devedu.bginventory.controller.dto;

import com.devedu.bginventory.domain.model.Boardgame;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

import static java.util.stream.Collectors.toList;

public record BoardgameDTO(Long id, @NotBlank String name, String description, @NotNull @Positive Integer releaseYear,
                           boolean isExpansion, boolean isStandalone, List<CategoryDTO> categories) {

    public BoardgameDTO(Boardgame bg) {
        this(bg.getId(), bg.getName(), bg.getDescription(), bg.getReleaseYear(),
                bg.isExpansion(), bg.isStandalone(),
                bg.getCategories().stream().map(CategoryDTO::new).collect(toList()));
    }
}
