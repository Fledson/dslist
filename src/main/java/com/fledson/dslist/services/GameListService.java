package com.fledson.dslist.services;

import com.fledson.dslist.dto.GameListDTO;
import com.fledson.dslist.entities.GameList;
import com.fledson.dslist.projections.GameMinProjection;
import com.fledson.dslist.repositories.GameListRepository;
import com.fledson.dslist.repositories.GameRepository;
import com.fledson.dslist.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> list = gameListRepository.findAll();
        return list.stream().map(GameListDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameListDTO findbyId(Long id) {
        GameList list = gameListRepository.findById(id)
                                .orElseThrow( () -> new ResourceNotFoundException("List not found, id list: " + id));
        return new GameListDTO(list);
    }

    /* Função que movimenta o jogo na lista*/
    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {
        /* pego a lista de projetos */
        List<GameMinProjection> list  = gameRepository.searchByList(listId);

        /* removo o jogo da posição atual e armazeno no obj*/
        GameMinProjection obj = list.remove(sourceIndex);

        /* Insiro ele na nova posição - aqui a lista já fica corretamente atualizada*/
        list.add(destinationIndex, obj);

        /* definindo a menor e a maior posição mexida */
        int min = Math.min(sourceIndex, destinationIndex);
        int max = Math.max(sourceIndex, destinationIndex);

        /* em um loop, percorro da menor posição alterada a maior e atualizo no banco a posição de cada item da lista */
        for(int position = min; position <= max; position++ ) {
            gameListRepository.updateBelongingPosition(
                    listId,
                    list.get(position).getId(),
                    position
            );
        }
    }
}
