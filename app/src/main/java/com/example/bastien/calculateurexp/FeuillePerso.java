package com.example.bastien.calculateurexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FeuillePerso extends ActionBarActivity {
    TextView force, endurance, dexterite, clairvoyance, sagesse, ce, education, charisme, rapidite, chance, discretion, metier, supp;
    TextView forceexp, enduranceexp, dexteriteexp, clairvoyanceexp, sagesseexp, ceexp, educationexp, charismeexp, rapiditeexp, chanceexp, discretionexp, metierexp, suppexp, totalexp;
    Button mforce, mendurance, mdexterite, mclairvoyance, msagesse, mce, meducation, mcharisme, mrapidite, mchance, mdiscretion, mmetier, msupp, save;
    Button pforce, pendurance, pdexterite, pclairvoyance, psagesse, pce, peducation, pcharisme, prapidite, pchance, pdiscretion, pmetier, psupp;
    DatabaseHandler db;
    EditText niveau;
    String nom="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feuille_perso);
        niveau=(EditText)findViewById(R.id.niveau);
        force=(TextView)findViewById(R.id.force);
        endurance=(TextView)findViewById(R.id.endurance);
        dexterite=(TextView)findViewById(R.id.dexterite);
        clairvoyance=(TextView)findViewById(R.id.clairvoyance);
        sagesse=(TextView)findViewById(R.id.sagesse);
        ce=(TextView)findViewById(R.id.ce);
        education=(TextView)findViewById(R.id.education);
        charisme=(TextView)findViewById(R.id.charisme);
        rapidite=(TextView)findViewById(R.id.rapidite);
        chance=(TextView)findViewById(R.id.chance);
        discretion=(TextView)findViewById(R.id.discretion);
        metier=(TextView)findViewById(R.id.metier);
        supp=(TextView)findViewById(R.id.supp);
        forceexp=(TextView)findViewById(R.id.forceexp);
        enduranceexp=(TextView)findViewById(R.id.enduranceexp);
        dexteriteexp=(TextView)findViewById(R.id.dexteriteexp);
        clairvoyanceexp=(TextView)findViewById(R.id.clairvoyanceexp);
        sagesseexp=(TextView)findViewById(R.id.sagesseexp);
        ceexp=(TextView)findViewById(R.id.ceexp);
        educationexp=(TextView)findViewById(R.id.educationexp);
        charismeexp=(TextView)findViewById(R.id.charismeexp);
        rapiditeexp=(TextView)findViewById(R.id.rapiditeexp);
        chanceexp=(TextView)findViewById(R.id.chanceexp);
        discretionexp=(TextView)findViewById(R.id.discretionexp);
        metierexp=(TextView)findViewById(R.id.metierexp);
        suppexp=(TextView)findViewById(R.id.suppexp);
        totalexp=(TextView)findViewById(R.id.totalexp);
        mforce=(Button)findViewById(R.id.mforce);
        mendurance=(Button)findViewById(R.id.mendurance);
        mdexterite=(Button)findViewById(R.id.mdexterite);
        mclairvoyance=(Button)findViewById(R.id.mclairvoyance);
        msagesse=(Button)findViewById(R.id.msagesse);
        mce=(Button)findViewById(R.id.mce);
        meducation=(Button)findViewById(R.id.meducation);
        mcharisme=(Button)findViewById(R.id.mcharisme);
        mrapidite=(Button)findViewById(R.id.mrapidite);
        mchance=(Button)findViewById(R.id.mchance);
        mdiscretion=(Button)findViewById(R.id.mdiscretion);
        mmetier=(Button)findViewById(R.id.mmetier);
        msupp=(Button)findViewById(R.id.msupp);
        save=(Button)findViewById(R.id.save);
        pforce=(Button)findViewById(R.id.pforce);
        pendurance=(Button)findViewById(R.id.pendurance);
        pdexterite=(Button)findViewById(R.id.pdexterite);
        pclairvoyance=(Button)findViewById(R.id.pclairvoyance);
        psagesse=(Button)findViewById(R.id.psagesse);
        pce=(Button)findViewById(R.id.pce);
        peducation=(Button)findViewById(R.id.peducation);
        pcharisme=(Button)findViewById(R.id.pcharisme);
        prapidite=(Button)findViewById(R.id.prapidite);
        pchance=(Button)findViewById(R.id.pchance);
        pdiscretion=(Button)findViewById(R.id.pdiscretion);
        pmetier=(Button)findViewById(R.id.pmetier);
        psupp=(Button)findViewById(R.id.psupp);
        assignerBouton(mforce, pforce, force, forceexp);
        assignerBouton(mendurance, pendurance, endurance, enduranceexp);
        assignerBouton(mdexterite, pdexterite, dexterite, dexteriteexp);
        assignerBouton(mclairvoyance, pclairvoyance, clairvoyance, clairvoyanceexp);
        assignerBouton(msagesse, psagesse, sagesse, sagesseexp);
        assignerBouton(mce, pce, ce, ceexp);
        assignerBouton(meducation, peducation, education, educationexp);
        assignerBouton(mcharisme, pcharisme, charisme, charismeexp);
        assignerBouton(mrapidite, prapidite, rapidite, rapiditeexp);
        assignerBouton(mchance, pchance, chance, chanceexp);
        assignerBouton(mdiscretion, pdiscretion, discretion, discretionexp);
        assignerBouton(mmetier, pmetier, metier,metierexp);
        assignerBouton(msupp, psupp, supp, suppexp);
        Intent intent = getIntent();

        if(intent.hasExtra("nom")) nom=intent.getStringExtra("nom");

        db = new DatabaseHandler(this);
        if(db.consulter(nom)) chargerDonnees();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Personnage p = new Personnage(nom, Integer.parseInt(niveau.getText().toString()), Integer.parseInt((String)force.getText()),Integer.parseInt((String)endurance.getText()),Integer.parseInt((String)dexterite.getText()),Integer.parseInt((String)clairvoyance.getText()),Integer.parseInt((String)sagesse.getText()),Integer.parseInt((String)ce.getText()),Integer.parseInt((String)education.getText()),Integer.parseInt((String)charisme.getText()),Integer.parseInt((String)rapidite.getText()),Integer.parseInt((String)chance.getText()),Integer.parseInt((String)discretion.getText()),Integer.parseInt((String)metier.getText()),Integer.parseInt((String)supp.getText()));
                Log.d("test", String.valueOf(p.getForce()+" "+p.getEndurance()+" "+p.getDexterite()) );
                db.ajouter(p);
            }
        });



    }

    private void assignerBouton(Button m, Button p, final TextView t, final TextView exp){

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ancienniveau = Integer.parseInt((String) t.getText());
                if (ancienniveau==1)return;
                int anciennexp=((String)exp.getText()).isEmpty()?0:Integer.parseInt((String)exp.getText());
                int ancientotal =((String)totalexp.getText()).isEmpty()?0:Integer.parseInt((String)totalexp.getText());
                t.setText(String.valueOf(ancienniveau-1));
                exp.setText(String.valueOf(anciennexp-calculXp(ancienniveau-1, ancienniveau)));
                totalexp.setText(String.valueOf(ancientotal-calculXp(ancienniveau-1, ancienniveau)));
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ancienniveau = Integer.parseInt((String) t.getText());
                int anciennexp=((String)exp.getText()).isEmpty()?0:Integer.parseInt((String)exp.getText());
                int ancientotal =((String)totalexp.getText()).isEmpty()?0:Integer.parseInt((String)totalexp.getText());
                t.setText(String.valueOf(ancienniveau + 1));
                exp.setText(String.valueOf(anciennexp+calculXp(ancienniveau, ancienniveau + 1)));
                totalexp.setText(String.valueOf(ancientotal+calculXp(ancienniveau, ancienniveau + 1)));
            }
        });
    }


    public int calculXp(int depart, int arrive){
        int experience[]={0,50,50,50,50,100,100,100,100,100,150,200,250,300,350,400,450,500,550,600,700,800,900,1000,1500,2000,2500,3000,3500,4000,4500,5000,5500,6000};

        int somme=0;
        for(int i =depart;i<arrive;i++){
            somme+=experience[i];
        }
        return somme;
    }

    public void chargerDonnees(){
        Log.d("test", "dans charger donnees");
        Personnage p = db.get(nom);
        niveau.setText(String.valueOf(p.getNiveau()));
        force.setText(String.valueOf(p.getForce()));
        endurance.setText(String.valueOf(p.getEndurance()));
        dexterite.setText(String.valueOf(p.getDexterite()));
        rapidite.setText(String.valueOf(p.getRapidite()));
        education.setText(String.valueOf(p.getEducation()));
        charisme.setText(String.valueOf(p.getCharisme()));
        clairvoyance.setText(String.valueOf(p.getClairvoyance()));
        ce.setText(String.valueOf(p.getCe()));
        metier.setText(String.valueOf(p.getMetier()));
        supp.setText(String.valueOf(p.getCaracsupp()));
        sagesse.setText(String.valueOf(p.getSagesse()));
        discretion.setText(String.valueOf(p.getDiscretion()));
        chance.setText(String.valueOf(p.getChance()));

    }
}
