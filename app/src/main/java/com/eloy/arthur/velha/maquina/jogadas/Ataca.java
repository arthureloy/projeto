package com.eloy.arthur.velha.maquina.jogadas;

import android.widget.ImageButton;

import com.eloy.arthur.velha.jogo.Game;
import com.eloy.arthur.velha.jogo.Games;

import java.util.List;

/**
 * Created by Pichau on 10/09/2017.
 */

public class Ataca implements Jogada {

    private List<ImageButton> buttons;

    public Ataca(List<ImageButton> buttons) {
        this.buttons = buttons;
    }

    @Override
    public ImageButton getCampo() {
        ImageButton b = melhorJogadaHandler();
        return b != null ? b : getJogadaPossivel();
    }

    private ImageButton melhorJogadaHandler() {
        MelhorJogada melhorJogada = new MelhorJogada();
        for (ImageButton b : buttons) {
            List<Game> games = ((Games) b.getTag()).getGames();
            int jogos = 0;
            for (Game g : games)
                if (g.estaVazio()){
                    ++jogos;
                }
            if (jogos > melhorJogada.getJogos()) {
                melhorJogada.setButton(b);
                melhorJogada.setJogos(jogos);
            }
        }
        return melhorJogada.getButton();
    }

    private ImageButton getJogadaPossivel() {
        for (ImageButton b : buttons)
            if (b.isEnabled()) return b;
        return null;
    }
}