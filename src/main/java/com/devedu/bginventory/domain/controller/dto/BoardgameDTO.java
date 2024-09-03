package com.devedu.bginventory.domain.controller.dto;

import com.devedu.bginventory.domain.model.Boardgame;

import java.util.List;

import static java.util.stream.Collectors.toList;

public record BoardgameDTO(Long id, String name, String description, Integer releaseYear,
                           boolean isExpansion, boolean isStandalone, List<CategoryDTO> categories) {

    public BoardgameDTO(Boardgame bg) {
        this(bg.getId(), bg.getName(), bg.getDescription(), bg.getReleaseYear(),
                bg.isExpansion(), bg.isStandalone(),
                bg.getCategories().stream().map(CategoryDTO::new).collect(toList()));
    }
}
