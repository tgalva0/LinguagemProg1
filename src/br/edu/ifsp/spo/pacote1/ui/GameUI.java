package br.edu.ifsp.spo.pacote1.ui;

import br.edu.ifsp.spo.pacote1.core.Player;
import br.edu.ifsp.spo.pacote1.itens.PlayerAction;
import br.edu.ifsp.spo.pacote1.itens.Card;
import br.edu.ifsp.spo.pacote1.rules.Scorer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GameUI {
    String requestPlayerName(int playerNumber);
    Scorer requestGameMode(Optional<Integer> TestModeInput);
    void printHand(List<Card> hand, int score);
    void renderPlayerTurn(Player player);
    PlayerAction requestAction(Player player);
    void printResult (Optional<Player> winner, int points, Player other, int otherPoints);
    void printMessage(String message);
    Player requestPlayerMode();
    void renderGeneralGameScore(Player player1, Player player2);
    int requestRoundNumber();
    void renderGeneralResult(Player player1, Player player2);
    List<String> findFileNames(List<Card> hand) ;
    void renderVisualHand(List<String> files) throws IOException;
}
