package com.fledson.dslist.services;

import com.fledson.dslist.dto.GameDTO;
import com.fledson.dslist.dto.GameListDTO;
import com.fledson.dslist.entities.Game;
import com.fledson.dslist.entities.GameList;
import com.fledson.dslist.repositories.GameListRepository;
import com.fledson.dslist.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GameListService {

    @Autowired
    private GameListRepository repository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> list = repository.findAll();
        return list.stream().map(GameListDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findbyId(Long id) {
        GameList list = repository.findById(id)
                                .orElseThrow( () -> new ResourceNotFoundException("List not found, id list: " + id));
        return new GameListDTO(list);
    }
}
