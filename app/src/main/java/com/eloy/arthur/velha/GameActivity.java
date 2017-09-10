package com.eloy.arthur.velha;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.eloy.arthur.velha.jogo.Game;
import com.eloy.arthur.velha.jogo.Games;
import com.eloy.arthur.velha.maquina.Maquina;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private static final int TOTAL_JOGADAS = 9;

    private Game l1, l2, l3, c1, c2, c3, d1, d2;
    private ImageButton[] mCampos = new ImageButton[9];
    private int mJogadas = 0;
    private int mJogador = 0;
    private String[] jogadores = {"Jogador 1", "Jogador 2"};
    private Maquina maquina;
    private int[] img = {R.drawable.icon_o, R.drawable.icon_x};
    Toolbar mToolbar;

    private View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //jogada do jogador
            ImageButton campoJogador = (ImageButton) v;
            Game gameJogador = ((Games) campoJogador.getTag()).adicionarGameParaJogador(mJogador);
            atualizaStatusJogo(gameJogador, campoJogador);

            //jogada da maquina
            ImageButton campoMaquina = maquina.handler();
            if (campoMaquina != null) {
                Game gameComputador = ((Games) campoMaquina.getTag()).adicionarGameParaJogador(mJogador);
                atualizaStatusJogo(gameComputador, campoMaquina);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);

        //lista dos botões
        mCampos[0] = (ImageButton) findViewById(R.id.b0);
        mCampos[1] = (ImageButton) findViewById(R.id.b1);
        mCampos[2] = (ImageButton) findViewById(R.id.b2);
        mCampos[3] = (ImageButton) findViewById(R.id.b3);
        mCampos[4] = (ImageButton) findViewById(R.id.b4);
        mCampos[5] = (ImageButton) findViewById(R.id.b5);
        mCampos[6] = (ImageButton) findViewById(R.id.b6);
        mCampos[7] = (ImageButton) findViewById(R.id.b7);
        mCampos[8] = (ImageButton) findViewById(R.id.b8);

        //lista das possibilidades de jogos para vitória
        l1 = new Game(mCampos[0], mCampos[1], mCampos[2]);
        l2 = new Game(mCampos[3], mCampos[4], mCampos[5]);
        l3 = new Game(mCampos[6], mCampos[7], mCampos[8]);
        c1 = new Game(mCampos[0], mCampos[3], mCampos[6]);
        c2 = new Game(mCampos[1], mCampos[4], mCampos[7]);
        c3 = new Game(mCampos[2], mCampos[5], mCampos[8]);
        d1 = new Game(mCampos[0], mCampos[4], mCampos[8]);
        d2 = new Game(mCampos[2], mCampos[4], mCampos[6]);

        //primeira linha de campos
        mCampos[0].setTag(new Games(l1, c1, d1));
        mCampos[1].setTag(new Games(l1, c2));
        mCampos[2].setTag(new Games(l1, c3, d2));
        //segunda linha de campos
        mCampos[3].setTag(new Games(l2, c1));
        mCampos[4].setTag(new Games(l2, c2, d1, d2));
        mCampos[5].setTag(new Games(l2, c3));
        //terceira linha de campos
        mCampos[6].setTag(new Games(l3, c1, d2));
        mCampos[7].setTag(new Games(l3, c2));
        mCampos[8].setTag(new Games(l3, c3, d1));

        resetarGame();

    }

    private void atualizaStatusJogo(Game game, ImageButton campo) {
        campo.setImageResource(img[mJogador]);
        campo.setEnabled(false);
        if (finalDoJogoHandler(game)) {
            mostrarDialogo();
        }
    }

    private void mostrarDialogo() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle(R.string.fim_de_jogo)
                .setMessage(R.string.recomeco)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetarGame();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    private boolean finalDoJogoHandler(Game game) {
        if (game != null) {
            return true;
        }
        if (velhaHandler(++mJogadas).equals(getString(R.string.velha))) {
            return true;
        }
        return false;
    }

    private String velhaHandler(int jogadas) {
        if (jogadas == TOTAL_JOGADAS){
            return getResources().getString(R.string.velha);
        }
        //Muda o jogador
        mJogador = (mJogador == 0) ? 1 : 0;
        return getResources().getString(R.string.jogador, jogadores[mJogador]);
    }

    public void resetarGame() {
        List<Game> games = listarJogos(l1, l2, l3, c1, c2, c3, d1, d2);
        for (Game game : games) {
            game.resetarGame();
        }
        mJogador = 0;
        mJogadas = 0;
        ativarBotoes(true);
        maquina = new Maquina(games, listarCampos());


        for (int b = 0; b < 9; b++) {
            mCampos[b].setImageBitmap(null);
            mCampos[b].setBackgroundResource(R.drawable.shape_campo_velha);
            mCampos[b].setOnClickListener(mClick);
        }
    }

    private void ativarBotoes(boolean status) {
        for (int b = 0; b < 9; b++)
            mCampos[b].setEnabled(status);
    }

    private List<Game> listarJogos(Game... games) {
        List<Game> jogosTemp = new ArrayList<>();
        for (Game game : games) {
            jogosTemp.add(game);
        }
        return jogosTemp;
    }

    private List<ImageButton> listarCampos() {
        List<ImageButton> campos = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            campos.add(mCampos[i]);
        return campos;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_resetar) {
            resetarGame();
            Toast.makeText(GameActivity.this, R.string.resetado, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
