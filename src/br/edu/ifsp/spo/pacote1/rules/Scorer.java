package br.edu.ifsp.spo.pacote1.rules;

import br.edu.ifsp.spo.pacote1.core.Player;
import br.edu.ifsp.spo.pacote1.itens.Card;

import java.util.List;
import java.util.Optional;

public interface Scorer {
    int calculateScore(List<Card> cards);

    default Optional<Player> verifyResult(Player player1, Player player2) {
        int scorePlay1 = this.calculateScore(player1.getHand());
        int scorePlay2 = this.calculateScore(player2.getHand());

        Player result = null;

        if(!(scorePlay1 == scorePlay2 || scorePlay1 > 21 && scorePlay2 > 21)) {
            if(scorePlay2>21) {
                result = player1;
            } else if(scorePlay1>21) {
                result = player2;
            } else {
                result = (scorePlay1 > scorePlay2) ? player1 : player2;
            }
        }
        return Optional.ofNullable(result);
    }

    default void calculateGameScore(Optional<Player> winner,Player player1, Player player2) {
        int scPlay1 = this.calculateScore(player1.getHand());
        int scPlay2 = this.calculateScore(player2.getHand());

        if(winner.isPresent()) {
            if(this.calculateScore(winner.get().getHand()) == 21) {
                winner.get().gameScore += 30;
            } else {
                if(scPlay1 > 21) {
                    player1.gameScore -= 5;
                    winner.get().gameScore += scPlay2;
                } else if(scPlay2 > 21) {
                    player2.gameScore -= 5;
                    winner.get().gameScore += scPlay1;
                } else {
                    winner.get().gameScore += ((scPlay1 - scPlay2) < 0) ? (scPlay1 - scPlay2) * -1 : scPlay1 - scPlay2;
                }
            }
        } else {
            if(scPlay1 == scPlay2 && scPlay1 <= 21) {
                if(scPlay1 == 21) {
                    player1.gameScore += 21;
                    player2.gameScore += 21;
                } else {
                    player1.gameScore += 10;
                    player2.gameScore += 10;
                }
            } else {
                player1.gameScore -= scPlay1 - 21;
                player2.gameScore -= scPlay2 - 21;
            }
        }
    }
}
