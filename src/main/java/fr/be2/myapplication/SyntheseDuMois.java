package fr.be2.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class SyntheseDuMois  extends Activity {


    //    private SQL_Helper dbHelper;
    private SimpleCursorAdapter dataAdapter;
    SQL_Helper BDD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese_du_mois);

        BDD = new SQL_Helper(this);
        BDD.open();


        //Générer le ListView a partir de SQLite Database
        displayListView();

    }

    private void displayListView() {


        Cursor cursor = BDD.fetchAllfrais();

        // Les colonnes que l’on veut lier
        String[] columns = new String[]{
                BDD.ID,
                BDD.Date,
                BDD.Libele,
                BDD.Quantite,
                BDD.Montant,
                BDD.DateSaisie,
        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[]{
                R.id.id,
                R.id.Date,
                R.id.Libelle,
                R.id.Quantite,
                R.id.Montant,
                R.id.dateSaisie,

        };


//On créer l'adaptateur à l'aide du curseur pointant sur les données souhaitées  ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.frais_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // On efface le fichier cliqué
                String myid =
                        cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Toast.makeText(getApplicationContext(),
                        myid, Toast.LENGTH_SHORT).show();
                BDD.deleteData(parseInt(myid));
                     displayListView();
            }
        }); }


    public void clique_Retour(View view){
        finish();
    }

}
