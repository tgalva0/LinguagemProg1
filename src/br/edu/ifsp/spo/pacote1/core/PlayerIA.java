package br.edu.ifsp.spo.pacote1.core;

import br.edu.ifsp.spo.pacote1.itens.PlayerAction;

public class PlayerIA extends Player {
    public PlayerIA() {
        super("R2D2");
    }

    public PlayerAction makeDecision (int score) {
        if(score >= 18){
            return PlayerAction.STAND;
        } else {
            return PlayerAction.HIT;
        }
    }


}
