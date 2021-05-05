//package com.crud.tasks.trello.config;
//
//import com.crud.tasks.controller.TaskController;
//import com.crud.tasks.controller.TrelloController;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//
//import static org.mockito.Mockito.when;
//
//@SpringJUnitWebConfig
//@WebMvcTest(TrelloController.class)
//public class TrelloConfigTestSuite {
//
//    @Mock
//    TrelloConfig trelloConfig;
//
//    @Test
//    public void testGetTrelloApiEndpoint() {
//        //given
//        String trelloApiEndpoint = "https://trello/1";
//        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(trelloApiEndpoint);
//        //when
//        String excpectApiEndpoint = trelloConfig.getTrelloApiEndpoint();
//        //then
//        Assert.assertEquals(trelloApiEndpoint, excpectApiEndpoint);
//    }
//    @Test
//    public void testGetTrelloAppKey() {
//        //given
//        String trelloAppKey = "8ds6a513ds16as3d6";
//        when(trelloConfig.getTrelloAppKey()).thenReturn(trelloAppKey);
//        //when
//        String expectedTrelloAppKey = trelloConfig.getTrelloAppKey();
//        //then
//        Assert.assertEquals(trelloAppKey, expectedTrelloAppKey);
//    }
//
//    @Test
//    public void testGetTrelloAppToken() {
//        //given
//        String trelloAppToken = "cf545632dsd665d2s60fef4e987e67";
//        when(trelloConfig.getTrelloToken()).thenReturn(trelloAppToken);
//        //when
//        String excpectTrelloAppToken = trelloConfig.getTrelloToken();
//        //then
//        Assert.assertEquals(trelloAppToken, excpectTrelloAppToken);
//    }
//
//    @Test
//    public void testGetTrelloUsername() {
//        //given
//        String trelloUsername = "";
//        when(trelloConfig.getTrelloUser()).thenReturn(trelloUsername);
//        //when
//        String expectedTrelloUsername = trelloConfig.getTrelloUser();
//        //then
//        Assert.assertEquals(trelloUsername, expectedTrelloUsername);
//    }
//}
