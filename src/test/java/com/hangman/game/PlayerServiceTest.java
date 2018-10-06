package com.hangman.game;

import com.hangman.game.constants.GlobalConstants;
import com.hangman.game.data.entity.Player;
import com.hangman.game.data.repository.PlayerRepository;
import com.hangman.game.response.JsonResponse;
import com.hangman.game.service.PlayerService;
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
public class PlayerServiceTest {

    @Spy
    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllPlayersAndOk() {
        Mockito.doReturn(TestData.getGameList()).when(playerRepository).findAll();
        ServiceResult<JsonResponse<List<Player>>> response = playerService.getAll();
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void getAllPlayersAndNotFound() {
        Mockito.doReturn(new ArrayList<>()).when(playerRepository).findAll();
        ServiceResult<JsonResponse<List<Player>>> response = playerService.getAll();
        Assert.assertEquals(GlobalConstants.GLOBAL_ERROR_MESSAGE, response.getResult().getDescription());
    }

    @Test
    public void savePlayerAndOk() {
        Mockito.doReturn(Optional.empty()).when(playerRepository).findById(TestData.getPlayerList().get(0).getId());
        Mockito.doReturn(TestData.getGameList()).when(playerRepository).findAll();
        ServiceResult<JsonResponse> response = playerService.savePlayer(TestData.getPlayerList().get(0));
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void savePlayerAndBadRequest() {
        Mockito.doReturn(Optional.of(TestData.getPlayerList().get(0))).when(playerRepository).findByName(TestData.getPlayerList().get(0).getName());
        Mockito.doReturn(TestData.getGameList()).when(playerRepository).findAll();
        ServiceResult<JsonResponse> response = playerService.savePlayer(TestData.getPlayerList().get(0));
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }

    @Test
    public void getPlayerAndOk() {
        Mockito.doReturn(Optional.of(TestData.getPlayerList().get(0))).when(playerRepository).findById(TestData.getPlayerList().get(0).getId());
        ServiceResult<JsonResponse<Player>> response = playerService.get(TestData.getPlayerList().get(0).getId());
        Assert.assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    public void getPlayerAndNotFound() {
        Mockito.doReturn(Optional.empty()).when(playerRepository).findById(TestData.getPlayerList().get(0).getId());
        ServiceResult<JsonResponse<Player>> response = playerService.get(TestData.getPlayerList().get(0).getId());
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getHttpStatus());
    }

}

    