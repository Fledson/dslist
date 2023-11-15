package com.fledson.dslist.services;

import com.fledson.dslist.dto.GameMinDTO;
import com.fledson.dslist.entities.Game;
import com.fledson.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        List<Game> list = repository.findAll();
        return list.stream().map(GameMinDTO::new).toList();
    }

}
