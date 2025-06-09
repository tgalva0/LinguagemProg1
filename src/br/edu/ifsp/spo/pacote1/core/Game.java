package br.edu.ifsp.spo.pacote1.core; //informação de qual pacote a classe pertence

import br.edu.ifsp.spo.pacote1.itens.Deck;
import br.edu.ifsp.spo.pacote1.itens.PlayerAction;
import br.edu.ifsp.spo.pacote1.rules.BasicScorer;
import br.edu.ifsp.spo.pacote1.rules.Scorer;
import br.edu.ifsp.spo.pacote1.ui.GameUI;
import br.edu.ifsp.spo.pacote1.ui.TerminalGameUI;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class Game {
    private static final Boolean DEFAULT_TEST_MODE = false;
    private Deck deck;
        private Player player1;
        private Player player2;
        private Scorer scorer;
        private final GameUI ui;
        private int roundNumber;

    // Constructor for the Game class
    public Game(Optional<Boolean> isTestMode) {
        this.ui = new TerminalGameUI();
        setupGameMode(isTestMode.orElse(DEFAULT_TEST_MODE));
    }

    // Private method to set up the game mode
    private void setupGameMode(boolean isTestMode) {
        if (isTestMode) {
            initializeTestMode();
        } else {
            initialize();
        }
    }


    private void initializeTestMode(){
            this.deck = new Deck();
            this.player1 = new PlayerIA();
            this.player2 = new PlayerIA();
            this.scorer = new BasicScorer();
            this.roundNumber = 1;

            for(int i=0; i<2; i++) {
                this.player1.receiveCard(deck.drawcard());
                this.player2.receiveCard(deck.drawcard());
            }
        }
        
        private void initialize()  {
            this.deck = new Deck();
            this.player1 = new Player(ui.requestPlayerName(1));
            this.player2 = ui.requestPlayerMode();
            this.scorer = ui.requestGameMode(Optional.empty());
            this.roundNumber = ui.requestRoundNumber();
            
            for(int i=0; i<2; i++) {
                this.player1.receiveCard(deck.drawcard());
                this.player2.receiveCard(deck.drawcard());
            }
        }

        public void restart() {
            if (this.deck.getSize() <= 0) {
                this.ui.printMessage("\nCartas Insuficientes no baralho, reembaralhando...");
                this.deck = new Deck();
                this.player1.restartHand();
                this.player2.restartHand();
            } else {
                this.deck.receiveDiscardedCards(this.player1.discardCards());
                this.deck.receiveDiscardedCards(this.player2.discardCards());
            }
            for (int i = 0; i < 2; i++) {
                this.player1.receiveCard(this.deck.drawcard());
                this.player2.receiveCard(this.deck.drawcard());
            }
        }

        public void play() throws IOException {
            Optional<Player> result;
            ui.renderGeneralGameScore(player1, player2);
            do {
                if(this.deck.getSize() <= 0) {
                    this.restart();
                    ui.printMessage("\nJogo reiniciado, Motivo: baralho estava zerado...");
                }
                Player[] players = {this.player1, this.player2};
                for (Player player : players) {
                    playerTurn(player);
                }
                result = scorer.verifyResult(player1, player2);
                var other = (result.isPresent() && result.get() == player1) ? player2 : player1;
                ui.printResult(result, result.map(player -> scorer.calculateScore(player.getHand())).orElse(0), other , scorer.calculateScore(other.getHand()));
                scorer.calculateGameScore(result, player1, player2);
                restart();
                roundNumber--;
            } while (roundNumber > 0);
            ui.renderGeneralResult(player1, player2);
        }

        private void playerTurn(Player player) throws IOException {
            ui.renderPlayerTurn(player);
            PlayerAction resposta;
            do {
                if(!(player instanceof PlayerIA)) {
                    ui.printHand(player.getHand(), scorer.calculateScore(player.getHand()));
                    ui.renderVisualHand(ui.findFileNames(player.getHand()));
                    resposta = ui.requestAction(player);
                } else {
                    resposta = ((PlayerIA) player).makeDecision(scorer.calculateScore(player.getHand()));
                }
                if (resposta == PlayerAction.HIT) {
                    if(this.deck.getSize() <= 0) {
                        ui.printMessage("\nERRO: baralho vazio");
                        break;
                    }
                    player.receiveCard(deck.drawcard());
                }
            } while (resposta == PlayerAction.HIT);
        }

// Getters used by tests

        public TerminalGameUI getUi() {
            return (TerminalGameUI) ui;
        }

        public Deck getDeck() {
            return this.deck;
        }

        public Player getPlayer1() {
            return this.player1;
        }

        public Player getPlayer2() {
            return this.player2;
        }

        public Scorer getScorer() {
            return this.scorer;
        }

        public int getRoundNumber() {
            return this.roundNumber;
        }


        @Override
        public String toString() {
            var resultado = "\nGame: 21";
            resultado += "\n\nplayer1: { "+this.player1.toString()+" }";
            resultado += "\n\nplayer2: {"+this.player2.toString()+" }";
            resultado += "\n\nbaralho: "+this.deck.getSize()+" cartas";

            return resultado;
        }
    }
