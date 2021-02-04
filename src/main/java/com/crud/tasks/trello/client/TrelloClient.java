package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    //@Autowired
    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoit.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloUser;

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = prepareUrl();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/kodillaautor/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .queryParam("name", trelloUser)
                .build()
                .encode()
                .toUri();



//        if(boardsResponse != null){
//            return Arrays.asList(boardsResponse);
        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
//        }
//        return Collections.emptyList();//todo
    }

    private URI prepareUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/kodillaautor/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .queryParam("name", trelloUser)
                .build()
                .encode()
                .toUri();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
             .queryParam("key", trelloAppKey)
             .queryParam("token", trelloToken)
             .queryParam("name", trelloCardDto.getName())
             .queryParam("desc",trelloCardDto.getDescription())
             .queryParam("pos", trelloCardDto.getPos())
             .queryParam("idList",trelloCardDto.getListId())
             .build()
             .encode()
             .toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
