package br.edu.ifsp.spo.pacote1.Tests;

import br.edu.ifsp.spo.pacote1.rules.AceElevenScorer;
import br.edu.ifsp.spo.pacote1.rules.BasicScorer;
import br.edu.ifsp.spo.pacote1.ui.TerminalGameUI;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TerminalGameUITest {
    TerminalGameUI ui = new TerminalGameUI();

    @Test
    void requestGameMode() {
        assertEquals(AceElevenScorer.class, ui.requestGameMode(Optional.of(2)).getClass());
        assertEquals(BasicScorer.class, ui.requestGameMode(Optional.of(1)).getClass());
    }
}