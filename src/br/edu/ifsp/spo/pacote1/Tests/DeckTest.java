package br.edu.ifsp.spo.pacote1.Tests;

import br.edu.ifsp.spo.pacote1.core.Player;
import br.edu.ifsp.spo.pacote1.itens.Card;
import br.edu.ifsp.spo.pacote1.itens.Deck;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck = new Deck();

    @Test
    void receiveDiscardedCards() {
        var player = new Player("sample_Player");
        player.receiveCard(deck.drawcard());
        var hand = player.getHand();
        assertEquals(51, deck.getSize());
        deck.receiveDiscardedCards(player.discardCards());
        assertEquals(hand, deck.getDiscartedCards());
    }

    @Test
    void drawcard() {
        var card = deck.drawcard();
        assertEquals(51, deck.getSize());
        assertNotNull(card);
    }
}