package com.example.bastien.calculateurexp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class  MesPersonnages extends ActionBarActivity {
    private FloatingActionButton fab;
    private DatabaseHandler db;
    private ListView list;
    private ArrayList<String> listNom;
    private String nomSelectionne;
    private int itemClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_personnages);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MesPersonnages.this);
                builder.setTitle("Nouveau Personnage");

// Set up the input
                final EditText input = new EditText(MesPersonnages.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        if(m_Text.isEmpty())dialog.cancel();
                        Intent intent = new Intent(MesPersonnages.this, FeuillePerso.class);
                        intent.putExtra("nom", m_Text);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        db = new DatabaseHandler(this);

        remplirList();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClicked=position;
            }
        });

        ScrollView sc =(ScrollView)findViewById(R.id.scrollView);
        sc.setOnTouchListener(new OnSwipeListener() {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(MesPersonnages.this, CalculDegat.class);
                startActivity(intent);

            }
        });
        list.setOnTouchListener(new OnSwipeListener() {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(MesPersonnages.this, CalculDegat.class);
                startActivity(intent);

            }

            public void onTouch(MotionEvent e) {
                Intent intent = new Intent(MesPersonnages.this, FeuillePerso.class);
                intent.putExtra("nom", listNom.get(itemClicked));
                startActivity(intent);
                Log.d("test", listNom.get(itemClicked));
                itemClicked=-1;
            }

            public void onLong(MotionEvent e){
                openContextMenu(list);
            }
        });
        registerForContextMenu(list);
    }


    public void remplirList() {
        List<Personnage> personnages = db.getAll();
        list = (ListView)findViewById(R.id.list);
        listNom = new ArrayList<String>();
        for(int i=0; i<personnages.size();i++){
            listNom.add(i, personnages.get(i).getNom());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listNom);
        list.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        remplirList();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
    }

    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.d("test", "dans menu context");
        ListView lv = (ListView) v;
        nomSelectionne = (String)listNom.get(itemClicked);
        itemClicked=-1;
        Log.d("test", nomSelectionne);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Afficher");
        menu.add(0, v.getId(), 0, "Supprimer");
        menu.add(0,v.getId(), 0, "Attaquer");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {

        super.onContextItemSelected(item);


        if(item.getTitle()=="Afficher"){
            Intent intent = new Intent(MesPersonnages.this, FeuillePerso.class);
            intent.putExtra("nom", nomSelectionne);
            startActivity(intent);
        }
        if(item.getTitle()=="Supprimer"){
            db.supprimer(nomSelectionne);
            remplirList();
        }
        if(item.getTitle()=="Attaquer"){
            Intent intent = new Intent(MesPersonnages.this, CalculDegatPersonnage.class);
            intent.putExtra("nom", nomSelectionne);
            startActivity(intent);
        }
        nomSelectionne=null;
        return true;

    }



}
