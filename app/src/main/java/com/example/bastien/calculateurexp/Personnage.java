package com.example.bastien.calculateurexp;

/**
 * Created by bastien on 16/02/16.
 */
public class Personnage {
    private int force, endurance, rapidite, dexterite, charisme, education, sagesse, ce, chance, clairvoyance, discretion, metier, caracsupp;
    private String nom;

    public Personnage(String nom, int force, int endurance, int dexterite, int clairvoyance, int sagesse, int ce, int education, int charisme, int rapidite, int chance,  int discretion, int metier, int caracsupp) {
        this.force = force;
        this.endurance = endurance;
        this.rapidite = rapidite;
        this.dexterite = dexterite;
        this.charisme = charisme;
        this.education = education;
        this.sagesse = sagesse;
        this.ce = ce;
        this.chance = chance;
        this.clairvoyance = clairvoyance;
        this.discretion = discretion;
        this.metier = metier;
        this.caracsupp = caracsupp;
        this.nom=nom;
    }

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getRapidite() {
        return rapidite;
    }

    public void setRapidite(int rapidite) {
        this.rapidite = rapidite;
    }

    public int getDexterite() {
        return dexterite;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getCharisme() {
        return charisme;
    }

    public void setCharisme(int charisme) {
        this.charisme = charisme;
    }

    public int getSagesse() {
        return sagesse;
    }

    public void setSagesse(int sagesse) {
        this.sagesse = sagesse;
    }

    public int getCe() {
        return ce;
    }

    public void setCe(int ce) {
        this.ce = ce;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getClairvoyance() {
        return clairvoyance;
    }

    public void setClairvoyance(int clairvoyance) {
        this.clairvoyance = clairvoyance;
    }

    public int getDiscretion() {
        return discretion;
    }

    public void setDiscretion(int discretion) {
        this.discretion = discretion;
    }

    public int getCaracsupp() {
        return caracsupp;
    }

    public void setCaracsupp(int caracsupp) {
        this.caracsupp = caracsupp;
    }

    public int getMetier() {
        return metier;
    }

    public void setMetier(int metier) {
        this.metier = metier;
    }


}
