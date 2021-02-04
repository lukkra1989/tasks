package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("lists")
    private List<TrelloListDto> lists;



    @JsonProperty("badges")
    private List<TrelloListDto>badges;

    @JsonProperty("votes")
    private List<TrelloListDto>votes;

    @JsonProperty("attachmentsByType")
    private List<TrelloListDto>attachmentsByType;

}
