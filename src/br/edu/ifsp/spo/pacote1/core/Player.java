package br.edu.ifsp.spo.pacote1.core;

import br.edu.ifsp.spo.pacote1.itens.Card;

import java.util.ArrayList;
import java.util.List;
public class Player {
    private final String name;
    private List <Card> hand;

    public int gameScore = 0;

    public void receiveCard(Card card) {
        this.hand.add(card);
    }
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public List<Card> getHand() {
        return hand;
    }

    public List<Card> discardCards() {
        List<Card> result = new ArrayList<>(this.hand);
        this.restartHand();
        return result;
    }


    public void restartHand() {
        this.hand.clear();
    }

    @Override
    public String toString() {
        var result = getName();
        result+="\n"+getHand();
        return result;
    }
}
