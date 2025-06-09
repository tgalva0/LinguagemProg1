package br.edu.ifsp.spo.pacote1.Tests;


import br.edu.ifsp.spo.pacote1.itens.PlayerAction;
import br.edu.ifsp.spo.pacote1.core.PlayerIA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerIATest {
    final private PlayerIA player = new PlayerIA();

    @Test
    void playerIsNotNull() {
        assertNotNull(player);
    }

    @Test
    void makeDecisionWhenGreaterThan18() {
        var response = player.makeDecision(19);
        assertEquals(PlayerAction.STAND, response);
    }

    @Test
    void makeDecisionWhen18OrLesser() {
        var response = player.makeDecision(17);
        assertEquals(PlayerAction.HIT, response);
    }

}