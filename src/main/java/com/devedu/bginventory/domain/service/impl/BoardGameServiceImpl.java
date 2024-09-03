package com.devedu.bginventory.domain.service.impl;

import com.devedu.bginventory.domain.controller.dto.BoardgameDTO;
import com.devedu.bginventory.domain.controller.dto.CategoryDTO;
import com.devedu.bginventory.domain.model.Boardgame;
import com.devedu.bginventory.domain.model.Category;
import com.devedu.bginventory.domain.repository.BoardgameRepository;
import com.devedu.bginventory.domain.repository.CategoryRepository;
import com.devedu.bginventory.domain.service.BoardgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardGameServiceImpl implements BoardgameService {

    @Autowired
    private BoardgameRepository boardgameRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardgameDTO> findAll() {
        return boardgameRepository.findAll().stream().map(BoardgameDTO::new).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BoardgameDTO findById(Long id) {
        var bg = boardgameRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Boardgame not found"));

        return new BoardgameDTO(bg);
    }

    @Override
    @Transactional
    public BoardgameDTO create(BoardgameDTO bgdto) {
        var bg = new Boardgame(bgdto);

        mapDtoToEntity(bgdto, bg);

        var createdBoardgame = boardgameRepository.save(bg);
        return new BoardgameDTO(createdBoardgame);
    }

    @Override
    @Transactional
    public BoardgameDTO update(Long id, BoardgameDTO bgdto) {
        var bg = boardgameRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Boardgame not found"));
        bg.setName(bgdto.name());
        bg.setDescription(bgdto.description());
        bg.setReleaseYear(bgdto.releaseYear());
        bg.setExpansion(bgdto.isExpansion());
        bg.setStandalone(bgdto.isStandalone());

        mapDtoToEntity(bgdto, bg);

        var createdBoardgame = boardgameRepository.save(bg);
        return new BoardgameDTO(createdBoardgame);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boardgameRepository.deleteById(id);
    }

    private void mapDtoToEntity(BoardgameDTO bgdto, Boardgame bg) {
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO categoryDTO : bgdto.categories()) {
            var category = categoryRepository.findById(categoryDTO.id()).orElseThrow(() -> new NoSuchElementException("Category not found"));
            categories.add(category);
        }
        bg.setCategories(categories);

    }
}
