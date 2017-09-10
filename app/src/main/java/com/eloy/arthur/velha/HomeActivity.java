package com.eloy.arthur.velha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Pichau on 10/09/2017.
 */
public class HomeActivity extends AppCompatActivity {

    ImageButton mBotaoJogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mBotaoJogar = (ImageButton) findViewById(R.id.bt_play);
        mBotaoJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, GameActivity.class);
                startActivity(i);

            }
        });
    }
}
