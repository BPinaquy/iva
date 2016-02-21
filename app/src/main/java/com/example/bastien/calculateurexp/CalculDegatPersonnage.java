package com.example.bastien.calculateurexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class CalculDegatPersonnage extends ActionBarActivity {
    private int chance;
    private int niveau;
    private Personnage p;
    private EditText  attaque, bonus, de, carac, technique;
    private Button calcul;
    private String nom;
    private TextView degat;
    private DatabaseHandler db;
    public ScrollView sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul_degat_personnage);
        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        if(intent.hasExtra("nom")) nom=intent.getStringExtra("nom");
        Log.d("test", nom);
        p = db.get(nom);
        chance = p.getChance();
        niveau = p.getNiveau();

        RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative);
        relative.setOnTouchListener(new com.example.bastien.calculateurexp.OnSwipeListener() {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(CalculDegatPersonnage.this, CalculExp.class);
                startActivity(intent);

            }
            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(CalculDegatPersonnage.this, MesPersonnages.class);
                startActivity(intent);

            }
        });
        sc=(ScrollView)findViewById(R.id.scrollView);
        sc.setOnTouchListener(new com.example.bastien.calculateurexp.OnSwipeListener() {
            @Override
            public void onSwipeLeft() {
                Intent intent = new Intent(CalculDegatPersonnage.this, CalculExp.class);
                startActivity(intent);

            }
            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(CalculDegatPersonnage.this, MesPersonnages.class);
                startActivity(intent);

            }
        });
        degat = (TextView) findViewById(R.id.degat);
        attaque = (EditText) findViewById(R.id.editTextAttaque);
        bonus = (EditText) findViewById(R.id.editTextBonus);
        de = (EditText) findViewById(R.id.editTextDe);
        technique = (EditText) findViewById(R.id.editTextTechnique);
        carac = (EditText) findViewById(R.id.editTextCarac);

        calcul = (Button) findViewById(R.id.button2);
        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int degatTotal = 0, chanceVal = 0, attaqueVal = 0, deVal = 0, bonusVal = 0, caracVal = 0, techniqueVal = 0;
                if(chance>=10&&niveau>=5)chanceVal=1;
                if(chance>=25&&niveau>=9)chanceVal=2;


                if (!attaque.getText().toString().equals("")) {
                    attaqueVal = Integer.valueOf(attaque.getText().toString());
                }

                if (!de.getText().toString().equals("")) {
                    deVal = Integer.valueOf(de.getText().toString());
                }

                if (!bonus.getText().toString().equals("")) {
                    bonusVal = Integer.valueOf(bonus.getText().toString());
                }

                if (!carac.getText().toString().equals("")) {
                    caracVal = Integer.valueOf(carac.getText().toString());
                }

                if (!technique.getText().toString().equals("")) {
                    techniqueVal = Integer.valueOf(technique.getText().toString());
                }


                if (deVal == 1 | deVal == 2) {
                    degat.setText("0");
                } else {
                    if (deVal == 20 || deVal == 19 && chanceVal == 1) {
                        degatTotal += deVal * 2;
                    } else if ((deVal == 20 || deVal == 19||deVal == 18 || deVal == 17) && chanceVal == 2) {
                        degatTotal += deVal * 2;
                    } else {
                        degatTotal += deVal;
                    }
                    degatTotal += attaqueVal + bonusVal + (techniqueVal * caracVal /100);
                    degat.setText(String.valueOf(degatTotal));
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calcul_degat_personnage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
