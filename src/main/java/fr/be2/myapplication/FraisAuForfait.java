package fr.be2.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.Calendar;

public class FraisAuForfait<JJ> extends MainActivity {
    SQL_Helper BDD;

    Spinner Type;
    EditText Quantite;
    EditText Date;

    //declaration d'un tableau avec les montants des frais au forfait
    Double[] tabMontant = new Double[]{0.0, 110.0, 0.62, 80.0, 25.0};
    //declaration d'un tableau avec les libelles des frais forfaits
    String[] tabType = new String[]{" ", "Forfait etape", "Frais KM", "Nuitee hotel", "Repas restaurant"};
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance();
    int aaaa = calendrier.get(Calendar.YEAR);
    int mm = calendrier.get(Calendar.MONTH);
    int jj = calendrier.get(Calendar.DAY_OF_MONTH);
    int date = (aaaa + '/' + mm + '/' + jj);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_au_forfait);
        BDD = new SQL_Helper(this);
        Type = findViewById(R.id.type);
        Quantite = findViewById(R.id.Quantite);
        Date = findViewById(R.id.Date);
    }

    public void save_Data(View view) {
        if (Quantite.getText().length()== 0 || Type.getSelectedItemPosition() == 0) { // si rien dans Quantite
             Toast.makeText(FraisAuForfait.this,"Erreur, valeur(s) manquante(s)" ,Toast.LENGTH_LONG).show();
            //  afficherMessage("ERREUR", "Type ou Quantite non saisi");
            return;
        } else {

            String Type1 = Type.getSelectedItem().toString();
            Integer Quantite1 = Integer.parseInt("0" + Quantite.getText().toString());
            String Date1 = Date.getText().toString();
            int position = Type.getSelectedItemPosition();
            Double Tarif = Quantite1 * tabMontant[position];

            if (BDD.insertData(Type1, Type1, Quantite1, Tarif, Date1)) {

                Quantite.setText("");
                Date.setText("");
                Toast.makeText(FraisAuForfait.this, "Frais enregistré", Toast.LENGTH_LONG).show();
                // afficherMessage("succes", "valeur ajoutée");

            }
        }
    }
        public void Picker (View vv)
        {
            picker = new DatePickerDialog(FraisAuForfait.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, aaaa, mm, jj);
            picker.show();
        }

        public void clique_Retour (View view){
            finish();
        }
    }

