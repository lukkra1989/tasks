package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards(){

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream()
                .filter(trelloBoardDto-> trelloBoards.contains("id" ))
                .filter(trelloBoardDto-> trelloBoards.contains("name" ))
                .filter(trelloBoardDto -> trelloBoards.contains("Kodilla"));
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
        });
    }
}
//    TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
//
//        if(boardsResponse != null){
//                return Arrays.asList(boardsResponse);
//                //return Optional.ofNullable(boardsResponse)
//                //   .map(Arrays::asList)
//                //   .orElse(Collections.emptyList());
//                }
//                return Collections.emptyList();//todo
//                }