package br.edu.ifsp.spo.pacote1.itens;

import org.w3c.dom.events.EventException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final ArrayList<Card> cards;
    private ArrayList<Card> discart;

//    static final String constante = "";  Notação de uma constante em java

    public Deck(){
        this.cards = new ArrayList<Card>();
        this.discart = new ArrayList<Card>();
        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank)) ;
            }
        }
        Collections.shuffle(cards);
    }

    public void receiveDiscardedCards(List<Card> cards) {
        for(Card card : cards) {
            if(!cards.contains(card)) {
                this.discart.add(card);
            }
        }
    }

    @Override
    public String toString() {
        return "Deck{" +
                "Quantidade=" + cards.size() +
                '}';
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card drawcard() {
        return this.cards.removeLast();
    }

    public int getSize() {
        return cards.size();
    }

    public ArrayList<Card> getDiscartedCards() {
        return discart;
    }
}
