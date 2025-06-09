package br.edu.ifsp.spo.pacote1.ui;

import br.edu.ifsp.spo.pacote1.core.Player;
import br.edu.ifsp.spo.pacote1.itens.PlayerAction;
import br.edu.ifsp.spo.pacote1.core.PlayerIA;
import br.edu.ifsp.spo.pacote1.rules.AceElevenScorer;
import br.edu.ifsp.spo.pacote1.rules.BasicScorer;
import br.edu.ifsp.spo.pacote1.itens.Card;
import br.edu.ifsp.spo.pacote1.rules.Scorer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TerminalGameUI implements GameUI {

    public String requestPlayerName(int playerNumber) {
        var scanner = new Scanner(System.in);
        System.out.println("\n\nDigite o nome do player " + playerNumber + ": ");
        return scanner.nextLine();
    }

    public Scorer requestGameMode(Optional<Integer> TestModeInput) {
        int response;
        if(TestModeInput.isEmpty()) {
            var scanner = new Scanner(System.in);
            System.out.println("\n\nVocê pode escolher entre dois modos de jogo: \nÁs valendo um (1)\nÁs valendo onze (2)\nDigite o número correspondente as regras que deseja: ");
            response =  scanner.nextInt();
        } else {
            response = TestModeInput.get();
        }

        if(response == 2) {
            return new AceElevenScorer();
        }
        return new BasicScorer();
    }

    public void printHand(List<Card> hand, int score) {
        System.out.println(hand + "\nscore: " + score);
    }

    @Override
    public PlayerAction requestAction(Player player) {
        var scanner = new Scanner(System.in);
        int resposta = 0;
        while(!(resposta == 1|| resposta == 2)) {
            System.out.println("\n\nQual ação você deseja fazer, " + player.getName() + "?: \nHIT(1)\nSTAND(2)");
            resposta = scanner.nextInt();
        }
        return switch(resposta) {
            case 1 -> PlayerAction.HIT;
            case 2 -> PlayerAction.STAND;
            default -> null;
        };
    }
    public void renderPlayerTurn(Player player) {
        System.out.println("\n\n\nVez de :" + player.getName() + "\nPontuação Geral: " + player.gameScore);
    }

    @Override
    public void printResult(Optional<Player> winner, int points, Player other, int otherPoints) {
        if(winner.isPresent()) {
            System.out.println("\n\n"+winner.get().getName() + " é o vencedor com " + points + " pontos!");
            System.out.println("\n"+other.getName() + " teve " + otherPoints + " pontos");
        } else {
            System.out.println("\n\nTivemos um empate! Vamos recomeçar");
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Player requestPlayerMode() {
        var scanner = new Scanner(System.in);
        int resposta = 0;

        while(!(resposta == 1|| resposta == 2)) {
            System.out.println("\n\nQual tipo de jogo deseja?: \nContra outro player(1)\nContra a IA(2)");
            resposta = scanner.nextInt();
        }
        if (resposta == 1) {
            return new Player(this.requestPlayerName(2));
        } else {
            return new PlayerIA();
        }
    }

    @Override
    public void renderGeneralGameScore(Player player1, Player player2) {
        System.out.printf("\n\n%s General Score: %s\n%s General Score: %s", player1.getName(), player1.gameScore, player2.getName(), player2.gameScore);
    }

    @Override
    public int requestRoundNumber() {
        var scanner = new Scanner(System.in);
        int resposta = 10;

        System.out.println("\n\nQuantas partidas deseja jogar? (default = 10) :");
        resposta = scanner.nextInt();

        return resposta;
    }

    @Override
    public void renderGeneralResult(Player player1, Player player2) {
        System.out.println("\n\n!RESULTADO GERAL!");
        if(player1.gameScore == player2.gameScore) {
            System.out.println("\nTivemos um empate, ambos tiveram " + player1.gameScore + " pontos.");
        } else {
            Player winner = (player1.gameScore > player2.gameScore) ? player1 : player2;
            Player loser = (player1.gameScore < player2.gameScore) ? player1 : player2;
            System.out.println("\n" + winner.getName() + " é o vencedor do placar geral com " + winner.gameScore + " pontos!!!\n" + loser.getName() + " somou " + loser.gameScore + " pontos no placar geral...");

        }
    }

    @Override
    public List<String> findFileNames(List<Card> hand)  {
        String fileName = "";
        List <String> filesList = new ArrayList<>();

        for(Card card : hand) {
            switch (card.getRank()) {
                case ACE -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/ace.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/ace.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/ace.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/ace.txt"; break;}
                    }
                }
                case TWO -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/two.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/two.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/two.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/two.txt"; break;}
                    }
                }
                case THREE -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/three.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/three.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/three.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/three.txt"; break;}
                    }
                }
                case FOUR -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/four.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/four.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/four.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/four.txt"; break;}
                    }
                }
                case FIVE -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/five.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/five.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/five.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/five.txt"; break;}
                    }
                }
                case SIX -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/six.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/six.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/six.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/six.txt"; break;}
                    }
                }
                case SEVEN -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/seven.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/seven.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/seven.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/seven.txt"; break;}
                    }
                }
                case EIGHT -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/eight.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/eight.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/eight.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/eight.txt"; break;}
                    }
                }
                case NINE -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/nine.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/nine.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/nine.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/nine.txt"; break;}
                    }
                }
                case TEN -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/ten.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/ten.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/ten.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/ten.txt"; break;}
                    }
                }
                case JACK -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/jack.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/jack.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/jack.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/jack.txt"; break;}
                    }
                }
                case QUEEN -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/queen.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/queen.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/queen.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/queen.txt"; break;}
                    }
                }
                case KING -> {
                    switch (card.getSuit()) {
                        case CLUBS -> { fileName = "src/resources/cards/clubs/king.txt"; break; }
                        case DIAMONDS -> { fileName = "src/resources/cards/diamonds/king.txt"; break;}
                        case HEARTS -> { fileName = "src/resources/cards/hearts/king.txt"; break;}
                        case SPADES -> { fileName = "src/resources/cards/spades/king.txt"; break;}
                    }
                }
            }
            filesList.add(fileName);
        }
        return filesList;
    }

    public void renderVisualHand(List<String> files) throws IOException {
        List<String[]> cartas = new ArrayList<>();

        // Ler cada arquivo e armazenar suas linhas
        for (String caminho : files) {
            try {
                List<String> linhas = Files.readAllLines(Paths.get(caminho));
                cartas.add(linhas.toArray(new String[0]));
            } catch (IOException e) {
                System.err.println("Erro ao ler arquivo: " + caminho);
            }
        }

        // Determinar o número máximo de linhas
        int maxLinhas = cartas.stream().mapToInt(arr -> arr.length).max().orElse(0);

        // Imprimir as cartas lado a lado
        for (int i = 0; i < maxLinhas; i++) {
            StringBuilder linhaMontada = new StringBuilder();
            for (String[] carta : cartas) {
                if (i < carta.length) {
                    linhaMontada.append(carta[i]).append(""); // Espaço entre cartas
                } else {
                    linhaMontada.append(" ".repeat(carta[0].length())).append(""); // Espaço vazio se faltar linhas
                }
            }
            System.out.println(linhaMontada);
        }
    }
}
