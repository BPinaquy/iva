package com.example.bastien.calculateurexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CalculExp extends ActionBarActivity {
    private Button button;
    private TextView resultat;
    private EditText depart, arrive;
    private int valDepart, valArrive;
    static int experience[]={0,50,50,50,50,100,100,100,100,100,150,200,250,300,350,400,450,500,550,600,700,800,900,1000,1500,2000,2500,3000,3500,4000,4500,5000,5500,6000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculexp);
        button = (Button)findViewById(R.id.button);
        resultat=(TextView)findViewById(R.id.resultat);
        depart=(EditText)findViewById(R.id.editText);
        arrive=(EditText)findViewById(R.id.editText2);
        RelativeLayout relative = (RelativeLayout)findViewById(R.id.relative);
        relative.setOnTouchListener(new OnSwipeListener(){
            @Override
            public void onSwipeRight() {
                Intent intent = new Intent(CalculExp.this,CalculDegat.class);
                startActivity(intent);

            }});


            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int somme=0;
                valDepart = Integer.parseInt(depart.getText().toString());
                valArrive = Integer.parseInt(arrive.getText().toString());
                resultat.setText(String.valueOf(calculXp(valDepart, valArrive)));
            }
        });
    }

    public static int calculXp(int depart, int arrive){
        int somme=0;
        for(int i =depart;i<arrive;i++){
            somme+=experience[i];
        }
        return somme;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
