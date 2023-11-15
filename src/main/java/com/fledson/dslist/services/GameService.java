package com.fledson.dslist.services;

import com.fledson.dslist.dto.GameDTO;
import com.fledson.dslist.dto.GameMinDTO;
import com.fledson.dslist.entities.Game;
import com.fledson.dslist.projections.GameMinProjection;
import com.fledson.dslist.repositories.GameRepository;
import com.fledson.dslist.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> list = repository.findAll();
        return list.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findbyId(Long id) {
        Game game = repository.findById(id)
                                .orElseThrow( () -> new ResourceNotFoundException("Game not found, game id: " + id));
        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId) {
        List<GameMinProjection> list  = repository.searchByList(listId);
        return list.stream().map(GameMinDTO::new).toList();
    }
}
