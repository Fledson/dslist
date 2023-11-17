package com.fledson.dslist.controllers;

import com.fledson.dslist.dto.GameListDTO;
import com.fledson.dslist.dto.GameMinDTO;
import com.fledson.dslist.dto.ReplacementDTO;
import com.fledson.dslist.services.GameListService;
import com.fledson.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;
    @Autowired
    private GameService gameService;


    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        return gameService.findByList(listId);
    }

    @PostMapping("/{listId}/replacement")
    public void movelist(@PathVariable Long listId, @RequestBody ReplacementDTO data) {
        gameListService.move(listId, data.sourceIndex(), data.destinationIndex());
    }
}
