package com.example.bastien.calculateurexp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bastien on 20/03/15.
 *
 *
 * Table ConnexionPerdue ==> ID = Nom du site colonne 1 heure  à laquelle on a pas joint le site ("joignable" sinon),colonne 3 1 si notification envoyé sinon 0
 * Table ConnexionPerdue ==> ID="internet" contient les informations sur la connexion internet du téléphone (date depuis laquelle on n'a plus internet, 0 en notification)
 *
 * Table NotificationsEnvoyées, sont enregistrés les identifiants des logs pour lesquels une notification a été envoyée
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String KEY = "Nom";


    public static final String TABLE_NAME_NOTIF = "Personnage";


    public static final String METIER_TABLE_NOTIF_CREATE =
            "CREATE TABLE " + TABLE_NAME_NOTIF + " (" +
                    KEY + " TEXT PRIMARY KEY, force INTEGER, endurance INTEGER, dexterite INTEGER, clairvoyance INTEGER, sagesse INTEGER, ce INTEGER, education INTEGER, charisme INTEGER, rapidite INTEGER, chance INTEGER, discretion INTEGER, mascarade INTEGER, metier INTEGER, supp1 INTEGER);";
    public static final String TABLE_DROP_NOTIF = "DROP TABLE IF EXISTS " + TABLE_NAME_NOTIF + ";";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "IVA";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP_NOTIF);
        onCreate(db);
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(METIER_TABLE_NOTIF_CREATE);
    }
    // Ajoute l'alerte "s" dans la table notification==> l'alerte a été signalée à l'utilisateur
    public void ajouter(Personnage personnage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(KEY, personnage.getNom());
        value.put("force", personnage.getForce());
        value.put("endurance", personnage.getEndurance());
        value.put("dexterite", personnage.getDexterite());
        value.put("clairvoyance", personnage.getClairvoyance());
        value.put("sagesse", personnage.getSagesse());
        value.put("ce", personnage.getCe());
        value.put("education", personnage.getEducation());
        value.put("charisme", personnage.getCharisme());
        value.put("rapidite", personnage.getRapidite());
        value.put("chance", personnage.getChance());
        value.put("discretion", personnage.getDiscretion());
        value.put("metier", personnage.getMetier());
        value.put("supp1", personnage.getCaracsupp());
        if(!consulter(personnage.getNom())) {
            db.insert(TABLE_NAME_NOTIF, null, value);
        }
        else{
            String selection = KEY + "=?";
            String[] selectionArgs = {personnage.getNom()};
            db.update(TABLE_NAME_NOTIF,value,selection,selectionArgs);
        }


    }
    // True si l'alerte a déja été signalée
    public boolean consulter(String s){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_NOTIF, new String[]{KEY,
                }, KEY + "=?",
                new String[]{s}, null, null, null, null);
        Boolean trouve = false;
        if (!cursor.isAfterLast()) {// Si le cursor n'est pas null
            cursor.moveToFirst();
            cursor.getString(0);
            trouve = true;
        }
        cursor.close();

        return trouve;
    }

    public Personnage get(String nom){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_NOTIF, new String[]{KEY, "force", "endurance", "dexterite", "clairvoyance", "sagesse", "ce", "education", "charisme", "rapidite", "chance", "discretion", "metier", "supp1"
                }, KEY + "=?",
                new String[]{nom}, null, null, null, null);
        Personnage p = null;
        if (!cursor.isAfterLast()) {// Si le cursor n'est pas null
            cursor.moveToFirst();
            p = new Personnage(cursor.getString(0),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)), Integer.parseInt(cursor.getString(12)), Integer.parseInt(cursor.getString(13)));

        }
        cursor.close();

        return p;
    }

    public List<Personnage> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME_NOTIF, new String[]{KEY, "force", "endurance", "dexterite", "clairvoyance", "sagesse", "ce", "education", "charisme", "rapidite", "chance", "discretion", "metier", "supp1"
                }, null,
                new String[]{}, null, null, null, null);
        Personnage p = null;
        List<Personnage> personnages = new ArrayList<Personnage>();
        if (!cursor.isAfterLast()) {// Si le cursor n'est pas null
            cursor.moveToFirst();
            do{
            p = new Personnage(cursor.getString(0),Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)), Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)), Integer.parseInt(cursor.getString(12)), Integer.parseInt(cursor.getString(13)));
            personnages.add(p);} while (cursor.moveToNext());
        }
        cursor.close();

        return personnages;
    }

    public void supprimer(String nom){
        Log.d("test", "supprimer " + nom);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        db.delete(TABLE_NAME_NOTIF, KEY +"=?",new String[]{nom});
    }
}