package br.edu.ifsp.spo.pacote1; //informação de qual pacote a classe pertence

import br.edu.ifsp.spo.pacote1.core.Game;

import java.io.IOException;
import java.util.Optional;

public class App {
    public static void main(String[] args) throws IOException {
        var game = new Game(Optional.empty());
        game.play();
    }
}