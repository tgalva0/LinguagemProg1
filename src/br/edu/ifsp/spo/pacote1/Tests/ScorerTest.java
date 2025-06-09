package br.edu.ifsp.spo.pacote1.Tests;

import br.edu.ifsp.spo.pacote1.core.Player;
import br.edu.ifsp.spo.pacote1.itens.*;
import br.edu.ifsp.spo.pacote1.rules.AceElevenScorer;
import br.edu.ifsp.spo.pacote1.rules.BasicScorer;
import br.edu.ifsp.spo.pacote1.rules.Scorer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScorerTest {
    final private Scorer basicScorer = new BasicScorer();
    final private Scorer aceToElevenScorer = new AceElevenScorer();
    final private Player player1 = new Player("Player1");
    final private Player player2 = new Player("Player2");

    void Setup(int scenario) {
        player1.gameScore = 0;
        player2.gameScore = 0;
        player1.restartHand();
        player2.restartHand();

        player1.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        player2.receiveCard(new Card(Suit.HEARTS, Rank.ACE));

        if(scenario == 6) {
            player1.receiveCard(new Card(Suit.CLUBS, Rank.TEN));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.FOUR));
        }

        if(scenario == 5) {
            player1.receiveCard(new Card(Suit.CLUBS, Rank.TEN));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.TEN));
        }

        if(scenario == 4) {
            player1.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.FOUR));
        }

        if(scenario == 3) {
            player1.receiveCard(new Card(Suit.CLUBS, Rank.TEN));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.TEN));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.JACK));
            player1.receiveCard(new Card(Suit.CLUBS, Rank.JACK));
        }

        if(scenario == 1 || scenario == 2) {
            player1.receiveCard(new Card(Suit.CLUBS, Rank.KING));
            player1.receiveCard(new Card(Suit.CLUBS, Rank.QUEEN));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.KING));
        }

        if (scenario == 1) {
            player1.receiveCard(new Card(Suit.CLUBS, Rank.JACK));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.JACK));
            player2.receiveCard(new Card(Suit.HEARTS, Rank.QUEEN));
        }
    }

    @Test
    void calculateScore() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.CLUBS, Rank.ACE));
        var basicScorerResult = basicScorer.calculateScore(hand);
        var aceToElevenScorerResult = aceToElevenScorer.calculateScore(hand);
        assertEquals(1, basicScorerResult);
        assertEquals(11, aceToElevenScorerResult);
    }

    @Test
    void verifyResultWhenPlayerWins() {
        this.Setup(2);
        var resultBasicSorer = basicScorer.verifyResult(player1, player2);
        var resultAceToElevenScorer = aceToElevenScorer.verifyResult(player1, player2);
        assertTrue(resultBasicSorer.isPresent());
        assertTrue(resultAceToElevenScorer.isPresent());
        assertEquals(resultBasicSorer.get(), player1);
        assertEquals(resultAceToElevenScorer.get(), player2);
    }

    @Test
    void verifyResultWhenBothPlayersLose() {
        this.Setup(1);
        var resultBasicSorer = basicScorer.verifyResult(player1, player2);
        var resultAceToElevenScorer = aceToElevenScorer.verifyResult(player1, player2);
        assertTrue(resultBasicSorer.isEmpty());
        assertTrue(resultAceToElevenScorer.isEmpty());
    }

    @Test
    void calculateGameScore_BasicScorer() {
        this.Setup(2); //Player achieves 21 points
        this.basicScorer.calculateGameScore(this.basicScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(30, player1.gameScore);
        assertEquals(0, player2.gameScore);

        this.Setup(1); //both overpass 21 points
        this.basicScorer.calculateGameScore(this.basicScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(-10, player1.gameScore);
        assertEquals(-10, player2.gameScore);

        this.Setup(0); //both get the same points less than 21
        this.basicScorer.calculateGameScore(this.basicScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(10, player1.gameScore);
        assertEquals(10, player2.gameScore);

        this.Setup(3); // both get 21 points
        this.basicScorer.calculateGameScore(this.basicScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(21, player1.gameScore);
        assertEquals(21, player2.gameScore);

        this.Setup(4); //one gets closer to 21 points
        this.basicScorer.calculateGameScore(this.basicScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(2, player1.gameScore);
        assertEquals(0, player2.gameScore);
    }

    @Test
    void calculateGameScore_AceToElevenScorer() {
        this.Setup(6); //Player achieves 21 points
        this.aceToElevenScorer.calculateGameScore(this.aceToElevenScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(30, player1.gameScore);
        assertEquals(0, player2.gameScore);

        this.Setup(1); //both overpass 21 points
        this.aceToElevenScorer.calculateGameScore(this.aceToElevenScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(-20, player1.gameScore);
        assertEquals(-20, player2.gameScore);

        this.Setup(0); //both get the same points less than 21
        this.aceToElevenScorer.calculateGameScore(this.aceToElevenScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(10, player1.gameScore);
        assertEquals(10, player2.gameScore);

        this.Setup(5); //both get 21 points
        this.aceToElevenScorer.calculateGameScore(this.aceToElevenScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(21, player1.gameScore);
        assertEquals(21, player2.gameScore);

        this.Setup(4); //one gets closer to 21 points
        this.basicScorer.calculateGameScore(this.basicScorer.verifyResult(player1, player2), player1, player2);
        assertEquals(2, player1.gameScore);
        assertEquals(0, player2.gameScore);

    }
}