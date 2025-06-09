package br.edu.ifsp.spo.pacote1.itens;

public class Card {
    private final Suit suit;    //Naipe
    private final Rank rank;    //Valor

    public Card (Suit Suit, Rank Rank) { //construtor
        this.suit = Suit;
        this.rank = Rank;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() { return suit; }

    @Override
    public String toString() {
        return "Card{" +
                "rank='" + rank + '\'' +
                ", suit='" + suit + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit.equals(card.suit) && rank.equals(card.rank);
    }

}
