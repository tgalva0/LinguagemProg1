package br.edu.ifsp.spo.pacote1.itens;

public enum Suit {
    HEARTS("Copas"),
    DIAMONDS("Ouros"),
    CLUBS("Paus"),
    SPADES("Espadas"); //são construtores

    private final String suitName;

    Suit(String suitName) { //construtor interno
        this.suitName = suitName;
    }
    @Override
    public String toString() {
        return this.suitName;
    }

//  Modificação: Modo de dar um nome especifico para os diferentes valores da classe enum;
}
