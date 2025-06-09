package br.edu.ifsp.spo.pacote1.rules;

import br.edu.ifsp.spo.pacote1.itens.Card;

import java.util.List;

public class AceElevenScorer implements Scorer {
    public int calculateScore(List<Card> cards) {
        var score = 0;
        for (Card card : cards) {
            switch (card.getRank()) {
                case ACE -> score+=11;
                case TWO -> score+=2;
                case THREE -> score+=3;
                case FOUR -> score+=4;
                case FIVE -> score+=5;
                case SIX -> score+=6;
                case SEVEN -> score+=7;
                case EIGHT -> score+=8;
                case NINE -> score+=9;
                case TEN, JACK, QUEEN, KING -> score+=10;
            }
        }
        return score;
    }
}
