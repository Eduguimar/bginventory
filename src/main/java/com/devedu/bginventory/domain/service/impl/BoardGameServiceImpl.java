package com.devedu.bginventory.domain.service.impl;

import com.devedu.bginventory.domain.controller.dto.BoardgameDTO;
import com.devedu.bginventory.domain.model.Boardgame;
import com.devedu.bginventory.domain.model.Category;
import com.devedu.bginventory.domain.repository.BoardgameRepository;
import com.devedu.bginventory.domain.service.BoardgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardGameServiceImpl implements BoardgameService {

    @Autowired
    private BoardgameRepository boardgameRepository;

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
        bg.setCategories(bgdto.categories().stream().map(Category::new).toList());

        var createdBoardgame = boardgameRepository.save(bg);
        return new BoardgameDTO(createdBoardgame);
    }

    @Override
    @Transactional
    public BoardgameDTO update(Long id, BoardgameDTO bgdto) {
        var bg = boardgameRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Boardgame not found"));
        bg.setName(bgdto.name());
        bg.setDescription(bgdto.description());
        bg.setYear(bgdto.year());
        bg.setExpansion(bgdto.isExpansion());
        bg.setStandalone(bgdto.isStandalone());
        bg.setCategories(bgdto.categories().stream().map(Category::new).toList());

        var createdBoardgame = boardgameRepository.save(bg);
        return new BoardgameDTO(createdBoardgame);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boardgameRepository.deleteById(id);
    }
}
