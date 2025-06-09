package br.edu.ifsp.spo.pacote1.Tests;

import br.edu.ifsp.spo.pacote1.core.Player;
import br.edu.ifsp.spo.pacote1.itens.Card;
import br.edu.ifsp.spo.pacote1.itens.Rank;
import br.edu.ifsp.spo.pacote1.itens.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    final private Player player = new Player("sample_Player");

    @Test
    void playerIsNotNull() {
        assertNotNull(player);
    }
    @Test
    void receiveCard() {
        var sampleCard = new Card(Suit.CLUBS, Rank.ACE);
        player.receiveCard(sampleCard);
        assertEquals(sampleCard, player.getHand().getLast());
    }

    @Test
    void discardCards() {
        var sampleCard = new Card(Suit.CLUBS, Rank.ACE);
        player.receiveCard(sampleCard);
        var discardedCards = player.discardCards();
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    void restartHand() {
        var sampleCard = new Card(Suit.CLUBS, Rank.ACE);
        player.receiveCard(sampleCard);
        player.restartHand();
        assertTrue(player.getHand().isEmpty());
    }
}