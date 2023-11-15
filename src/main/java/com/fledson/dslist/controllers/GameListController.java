package com.fledson.dslist.controllers;

import com.fledson.dslist.dto.GameDTO;
import com.fledson.dslist.dto.GameListDTO;
import com.fledson.dslist.dto.GameMinDTO;
import com.fledson.dslist.services.GameListService;
import com.fledson.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService service;
    @Autowired
    private GameService gameService;


    @GetMapping
    public List<GameListDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        return gameService.findByList(listId);
    }

}
