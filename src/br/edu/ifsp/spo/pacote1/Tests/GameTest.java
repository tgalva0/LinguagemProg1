package br.edu.ifsp.spo.pacote1.Tests;

import br.edu.ifsp.spo.pacote1.core.Game;
import br.edu.ifsp.spo.pacote1.itens.Card;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game(Optional.of(true));

    GameTest() throws IOException {
    }

    @Test
    void initializer() {
        assertNotNull(game.getUi());
        assertNotNull(game.getDeck());
        assertNotNull(game.getScorer());
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertNotNull(game.getPlayer1().getHand());
        assertNotNull(game.getPlayer2().getHand());
//        nothing should be null
    }

    @Test
    void restarted() {
        game.restart();
        int DeckSize = game.getDeck().getCards().size();
        for(int i = 0; i < DeckSize; i++) {
            game.getPlayer1().receiveCard(game.getDeck().drawcard());
        }
        assertTrue(game.getDeck().getCards().isEmpty());
        for(int i = 0; i < 2; i++) { //do it twice to verify both possible scenarios
            game.restart();
            assertFalse(game.getDeck().getCards().isEmpty());
            assertFalse(game.getPlayer1().getHand().isEmpty());
            assertFalse(game.getPlayer2().getHand().isEmpty());
            for (Card card : game.getPlayer1().getHand()) {
                assertFalse(game.getDeck().getCards().contains(card));
            }
            for (Card card : game.getPlayer2().getHand()) {
                assertFalse(game.getDeck().getCards().contains(card));
            }
        }
    }

    @Test
    void play () throws IOException {
        assertEquals(1, game.getRoundNumber());
        game.play(); //should decrease round number to 0
        assertEquals(0, game.getRoundNumber());

    }
}