package com.fledson.dslist.repositories;

import com.fledson.dslist.entities.Game;
import com.fledson.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {

}
