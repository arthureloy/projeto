package com.eloy.arthur.velha.jogo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pichau on 10/09/2017.
 */

public class Games {
    private List<Game> games = new ArrayList<>();

    public Games(Game... games) {
        for (Game game : games)
            this.games.add(game);
    }

    public Game adicionarGameParaJogador(int jogador) {
        Game game;
        for (Game j : games) {
            game = j.adicionarGameNaSerieDoJogador(jogador);
            if (game != null)
                return game;
        }
        return null;
    }

    public List<Game> getGames() {
        return this.games;
    }
}
