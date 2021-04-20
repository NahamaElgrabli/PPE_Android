package fr.be2.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.SQLException;
import android.util.Log;

public class SQL_Helper extends SQLiteOpenHelper {

        private static final String DB_NAME = "GSB_Database.db";
        private static final String DB_TABLE = "Frais_table";
        public static final String ID = "ID";
        private static final String Type = "Type_Frais";
        public static final String Libele = "Libele";
        public static final String Quantite = "Quantite";
        public static final String Montant = "Montant";
        public static final String Date = "Date";
        public static final String DateSaisie = "DateSaisie";

    private SQL_Helper   mDbHelper;

    private static final String TAG = "SQL_HELPER";
    private SQLiteDatabase mDb;
   // private final Context mCtx;

       private static final String CREATE_TABLE = "CREATE TABLE "+DB_TABLE+" ("+ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ Type +" TEXT"+","+ Libele+" TEXT"+","+ Quantite+" INTEGER"+","+ Montant+" REAL"+","+ Date +" TEXT,"+"DateSaisie DATETIME DEFAULT CURRENT_TIMESTAMP"+" )";

    // private static final String CREATE_TABLE = "CREATE TABLE DB_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, TypeFrais TEXT, Libelle TEXT, Quantite Integer, Montant Float, Date String)";
        public SQL_Helper(Context context){

            super(context,DB_NAME,null,1);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(sqLiteDatabase);
        }

        public boolean insertData(String libele, String type , Integer quantite, double montant, String date){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Type, type);
            contentValues.put(Libele, libele);
            contentValues.put(Quantite,quantite);
            contentValues.put(Montant,montant);
            contentValues.put(Date,date);
            long result = db.insert(DB_TABLE, null, contentValues);
            return result != -1;

        }
        public Cursor viewData(){
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "select * from "+DB_TABLE ;
            Cursor pointeur = db.rawQuery(query, null);
            return pointeur;

        }
        public boolean deleteData(Integer ID){
            SQLiteDatabase db = this.getWritableDatabase();

            long result = db.delete(DB_TABLE,"ID="+ID,null);

            return result != -1;

        }



    public SQL_Helper open() throws SQLException {
        //mDbHelper = new SQL_Helper(mCtx);
        mDb = this.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }


    public Cursor fetchFraisByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(DB_TABLE, new String[] {ID, Date,
                            Libele, Montant,Quantite, DateSaisie},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, DB_TABLE, new String[] {ID, Date,
                            Libele, Montant,Quantite, DateSaisie},
                    Date + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor fetchAllfrais() {

        Cursor mCursor = mDb.query(DB_TABLE, new String[] {"rowid _id",ID, Date,
                        Libele, Montant,Quantite, DateSaisie},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    }
