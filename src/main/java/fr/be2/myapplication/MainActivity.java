package fr.be2.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {

   @Override
      protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);}

     public void onClick(View view){
        if (view.getId() == R.id.buttonFraisForfait) {
            Intent intent = new Intent(getApplicationContext(), FraisAuForfait.class);
            startActivity(intent);
        }
        if (view.getId()== R.id.buttonFraisHorsForfait) {
            Intent intent = new Intent(getApplicationContext(), FraisHorsForfait.class);
            startActivity(intent);
        }
        if (view.getId()== R.id.buttonSynthese) {
            Intent intent = new Intent(getApplicationContext(), SyntheseDuMois.class);
            startActivity(intent);
        }
        if (view.getId()== R.id.buttonEnvoi) {
            Intent intent = new Intent(getApplicationContext(), EnvoiDesDonnees.class);
            startActivity(intent);
        }
        if (view.getId()== R.id.buttonParametres) {
            Intent intent = new Intent(getApplicationContext(), Parametres.class);
            startActivity(intent);
        }



     }
     public void afficherMessage(String titre ,String message){
         AlertDialog.Builder Builder = new AlertDialog.Builder(this) ;
         Builder.setCancelable(true) ;
         Builder.setTitle(titre) ;
         Builder.setMessage(message) ;
         Builder.show() ;
     }

   }

