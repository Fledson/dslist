package com.fledson.dslist.controllers;

import com.fledson.dslist.dto.GameDTO;
import com.fledson.dslist.dto.GameMinDTO;
import com.fledson.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public List<GameMinDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable Long id) {
        return service.findbyId(id);
    }

}
