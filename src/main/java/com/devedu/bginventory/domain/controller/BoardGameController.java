package com.devedu.bginventory.domain.controller;

import com.devedu.bginventory.domain.controller.dto.BoardgameDTO;
import com.devedu.bginventory.domain.service.BoardgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/boardgames")
public class BoardGameController {

    @Autowired
    private BoardgameService boardgameService;

    @GetMapping
    public ResponseEntity<List<BoardgameDTO>> findAll() {
        return ResponseEntity.ok(boardgameService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardgameDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(boardgameService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BoardgameDTO> create(@RequestBody BoardgameDTO boardgameDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardgameService.create(boardgameDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardgameDTO> update(@PathVariable Long id, @RequestBody BoardgameDTO boardgameDTO) {
        return ResponseEntity.ok(boardgameService.update(id, boardgameDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Boardgame deleted successfully");
    }

}
