package com.eloy.arthur.velha.maquina;

import android.widget.ImageButton;

import com.eloy.arthur.velha.jogo.Game;
import com.eloy.arthur.velha.maquina.jogadas.Ataca;
import com.eloy.arthur.velha.maquina.jogadas.Avanca;
import com.eloy.arthur.velha.maquina.jogadas.Jogada;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pichau on 10/09/2017.
 */

public class Maquina {

    public static final int ID_JOGADOR = 0;
    public static final int ID_COMPUTADOR = 1;

    private List<Jogada> jogadas = new ArrayList<>();

    public Maquina(List<Game> games, List<ImageButton> buttons) {
        this.jogadas.add(new Avanca(games, Maquina.ID_COMPUTADOR, 2)); //finaliza
        this.jogadas.add(new Avanca(games, Maquina.ID_JOGADOR, 2)); //bloqueia
        this.jogadas.add(new Avanca(games, Maquina.ID_COMPUTADOR, 1)); //continua
        this.jogadas.add(new Ataca(buttons)); //ataca
    }

    public ImageButton handler() {
        ImageButton b;
        for (Jogada j : jogadas) {
            b = j.getCampo();
            if (b != null) return b;
        }
        return null;
    }
}
