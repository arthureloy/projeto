package com.eloy.arthur.velha.maquina.jogadas;

import android.widget.ImageButton;

import com.eloy.arthur.velha.jogo.Game;

import java.util.List;

/**
 * Created by Pichau on 10/09/2017.
 */

public class Avanca implements Jogada {

    private int mJogador;
    private int pontos;
    private List<Game> games;

    public Avanca(List<Game> games, int jogador, int pontos) {
        this.games = games;
        this.mJogador = jogador;
        this.pontos = pontos;
    }

    @Override
    public ImageButton getCampo() {
        Game game = getGame();

        if (game != null) {
            List<ImageButton> buttons = game.getCampos();
            for (ImageButton button : buttons)
                if (button.isEnabled()) return button;
        }
        return null;
    }

    private Game getGame() {
        for (Game game : games)
            if (game.getPontos(mJogador) == pontos)
                return game;
        return null;
    }
}