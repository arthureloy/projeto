package com.eloy.arthur.velha.jogo;

import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pichau on 10/09/2017.
 */

public class Game {

    private List<ImageButton> mCampos = new ArrayList<>();
    private int[] mGamesJogadores = new int[2];

    public Game(ImageButton ... campos) {
        for (ImageButton campo : campos){
            this.mCampos.add(campo);
        }

        this.mGamesJogadores[0] = 0;
        this.mGamesJogadores[1] = 0;
    }

    public Game adicionarGameNaSerieDoJogador(int jogador) {
        Game game = (++mGamesJogadores[jogador] < 3) ? null : this;
        return game;
    }

    public int getPontos(int jogador) {
        int inimigo = jogador == 0 ? 1 : 0;
        if (mGamesJogadores[jogador] > 0 && mGamesJogadores[inimigo] == 0) {
            return mGamesJogadores[jogador];
        }
        return 0;
    }

    public void resetarGame() {
        this.mGamesJogadores[0] = 0;
        this.mGamesJogadores[1] = 0;
    }

    public boolean estaVazio() {
        return (mGamesJogadores[0] == 0 && mGamesJogadores[1] == 0);
    }

    public List<ImageButton> getCampos() {
        return this.mCampos;
    }

}
