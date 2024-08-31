package com.devedu.bginventory.domain.repository;

import com.devedu.bginventory.domain.model.Boardgame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Long> {
}
