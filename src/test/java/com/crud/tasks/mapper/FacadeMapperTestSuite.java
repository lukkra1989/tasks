package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mappper.TrelloMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class FacadeMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("1", "First List", false);
        TrelloList trelloList2 = new TrelloList("2", "Second List", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);
        TrelloBoard trelloBoard = new TrelloBoard("1", "Board1", trelloLists);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        Assert.assertNotEquals(trelloBoardList, trelloBoardDtoList);
        Assert.assertEquals(1, trelloBoardDtoList.size());
        Assert.assertEquals(2, trelloBoardDtoList.get(0).getLists().size());
        Assert.assertEquals("1", trelloBoardDtoList.get(0).getLists().get(0).getId());
        Assert.assertEquals("First List", trelloBoardDtoList.get(0).getLists().get(0).getName());
        Assert.assertTrue(trelloBoardDtoList.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToBoard() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("3", "The List", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("4", "Uber List", false);
        List<TrelloListDto> trelloLists = Arrays.asList(trelloListDto1, trelloListDto2);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "Board1", trelloLists);
        List<TrelloBoardDto> trelloBoardListDto = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardListDto);

        //Then
        Assert.assertNotEquals(trelloBoardListDto, trelloBoardList);
        Assert.assertEquals(1, trelloBoardList.size());
        Assert.assertEquals(2, trelloBoardList.get(0).getLists().size());
        Assert.assertEquals("3", trelloBoardList.get(0).getLists().get(0).getId());
        Assert.assertEquals("The List", trelloBoardList.get(0).getLists().get(0).getName());
        Assert.assertFalse(trelloBoardList.get(0).getLists().get(1).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("5", "ABC", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("6", "XYZ", false);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto1, trelloListDto2);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        Assert.assertNotEquals(trelloListsDto, trelloLists);
        Assert.assertEquals(2, trelloLists.size());
        Assert.assertEquals("5", trelloLists.get(0).getId());
        Assert.assertEquals("XYZ", trelloLists.get(1).getName());
        Assert.assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("7", "ABC", true);
        TrelloList trelloList2 = new TrelloList("8", "XYZ", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assert.assertNotEquals(trelloLists, trelloListsDto);
        Assert.assertEquals(2, trelloListsDto.size());
        Assert.assertEquals("7", trelloListsDto.get(0).getId());
        Assert.assertEquals("XYZ", trelloListsDto.get(1).getName());
        Assert.assertFalse(trelloListsDto.get(1).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto1 = new TrelloCardDto("Card 1", "lorem ipsum", "11", "1234");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto1);

        //Then
        Assert.assertEquals("Card 1", trelloCard.getName());
        Assert.assertEquals("lorem ipsum", trelloCard.getDescription());
        Assert.assertEquals("11", trelloCard.getPos());
        Assert.assertEquals("1234", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard1 = new TrelloCard("Card 2", "Example Desc.", "2", "4321");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard1);

        //Then
        Assert.assertEquals("Card 2", trelloCardDto.getName());
        Assert.assertEquals("Example Desc.", trelloCardDto.getDescription());
        Assert.assertEquals("2", trelloCardDto.getPos());
        Assert.assertEquals("4321", trelloCardDto.getListId());
    }
}