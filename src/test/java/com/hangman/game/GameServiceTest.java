package com.hangman.game;

import com.hangman.game.constants.GlobalConstants;
import com.hangman.game.data.entity.Game;
import com.hangman.game.data.repository.GameRepository;
import com.hangman.game.response.JsonResponse;
import com.hangman.game.service.GameService;
import com.hangman.game.service.ServiceResult;
import data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
public class GameServiceTest {

    @Spy
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllGamesAndOk() {
        Mockito.doReturn(TestData.getGameList()).when(gameRepository).findAll();
        ServiceResult<JsonResponse<List<Game>>> response = gameService.getAll();
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void getAllGamesAndNotFound() {
        Mockito.doReturn(new ArrayList<>()).when(gameRepository).findAll();
        ServiceResult<JsonResponse<List<Game>>> response = gameService.getAll();
        Assert.assertEquals(GlobalConstants.GLOBAL_ERROR_MESSAGE, response.getResult().getDescription());
    }

    @Test
    public void getGameAndOk() {
        Mockito.doReturn(Optional.of(TestData.getGameList().get(0))).when(gameRepository).findById(TestData.getGameList().get(0).getId());
        ServiceResult<JsonResponse<Game>> response = gameService.get(TestData.getGameList().get(0).getId());
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void getGameAndNotFound() {
        ServiceResult<JsonResponse<Game>> response = gameService.get(TestData.getGameList().get(0).getId());
        Assert.assertEquals(GlobalConstants.GAME_NOT_FOUND_MESSAGE, response.getResult().getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void getGameAndNullPointerException() {
        Mockito.doReturn(null).when(gameRepository).findById(TestData.getGameList().get(0).getId());
        ServiceResult<JsonResponse<Game>> response = gameService.get(TestData.getGameList().get(0).getId());
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void startGameAndOk() {
        ServiceResult<JsonResponse> response = gameService.startGame(TestData.getPlayerList().get(0));
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test(expected = RuntimeException.class)
    public void startGameAndRuntimeException() {
        Mockito.doNothing().doThrow(new RuntimeException()).when(gameRepository).save(TestData.getGameList().get(0));
        ServiceResult<JsonResponse> response = gameService.startGame(TestData.getPlayerList().get(0));
    }

    @Test
    public void guessAndOk() {
        Mockito.doReturn(Optional.of(TestData.getGameList().get(0))).when(gameRepository).findById(TestData.getGameList().get(0).getId());
        ServiceResult<JsonResponse> response = gameService.guess(TestData.getGameList().get(0).getId(), TestData.getGuess());
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void guessAndNotFound() {
        Mockito.doReturn(Optional.empty()).when(gameRepository).findById(TestData.getGameList().get(0).getId());
        ServiceResult<JsonResponse> response = gameService.guess(TestData.getGameList().get(0).getId(), TestData.getGuess());
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
    }

    @Test
    public void finishGameAndOk() {
        Mockito.doReturn(Optional.of(TestData.getGameList().get(0))).when(gameRepository).findById(TestData.getGameList().get(0).getId());
        ServiceResult<JsonResponse> response = gameService.finishGame(TestData.getGameList().get(0).getId());
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void finishGameAndNotFound() {
        Mockito.doReturn(Optional.empty()).when(gameRepository).findById(TestData.getGameList().get(0).getId());
        ServiceResult<JsonResponse> response = gameService.finishGame(TestData.getGameList().get(0).getId());
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
    }
}

    