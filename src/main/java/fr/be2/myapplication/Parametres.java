package fr.be2.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Parametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
    }
    /**
     * Effectue un retour en arrière soit arrete l'activité en cours
     *
     * @param view
     *
     * @return null
     */
    public void clique_Retour(View view){
        finish();
    }
}
