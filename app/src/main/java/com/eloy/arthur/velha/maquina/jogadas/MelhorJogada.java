package com.eloy.arthur.velha.maquina.jogadas;

import android.widget.ImageButton;

/**
 * Created by Pichau on 10/09/2017.
 */

public class MelhorJogada {

    private ImageButton button;
    private int jogos;

    public MelhorJogada() {
        this.jogos = 0;
        this.button = null;
    }

    public void setButton(ImageButton button) {
        this.button = button;
    }

    public ImageButton getButton() {
        return  this.button;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    public int getJogos() {
        return jogos;
    }
}