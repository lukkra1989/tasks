package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Service
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrelloServiceTest {



    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;


    @Mock
    private Mail mail;

    @Test
    public void fetchTrelloBoards() {
        //Given
        List<TrelloListDto> listToTest = Arrays.asList(new TrelloListDto("1", "testedList", true));
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1.1", "testedBoard", listToTest);
        List<TrelloBoardDto> trelloBoardToTest = Arrays.asList(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardToTest);
        //When
        List<TrelloBoardDto> fetchedList = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(trelloBoardToTest.size(), 1);
    }

    @Test
    public void createdTrelloCard() {
        //Given
        TrelloCardDto cardToTest = new TrelloCardDto("1", "something", "top", "22");
        CreatedTrelloCardDto createdToTest = new CreatedTrelloCardDto("1", "1", "some_url");
        when(trelloClient.createNewCard(cardToTest)).thenReturn(createdToTest);
        when(adminConfig.getAdminMail()).thenReturn("admin@admin.com");

        //When
        CreatedTrelloCardDto createdCard = trelloService.createdTrelloCard(cardToTest);
        //Then
        assertEquals(createdToTest, createdCard);
        verify(emailService, times(1)).send(mail);
    }

    @Test
    public void shouldNotSendEmail() {
        //Given
        //When
        trelloService.createdTrelloCard(null);
        //Then
        verify(emailService, times(0)).send(mail);
    }
}
