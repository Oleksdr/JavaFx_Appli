package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    private final GenEvent genevent;

    private String nom;
    private ArrayList<Membre> membresPermanents = new ArrayList<>();
    private CaisseASavon caisseASavon;

    public Equipe(GenEvent genevent, String nom) {
        this.genevent = genevent;
        setNom(nom);
    }

    //Getters et Setters ---------------------------------------------


    public void setNom(String nom) {
        this.nom = nom.trim();
    }

    public void setMembresPermanents(ArrayList<Membre> membresPermanents) {
        this.membresPermanents = membresPermanents;
    }


    public String getNom() {
        return nom;
    }

    public ArrayList<Membre> getMembresPermanents() {
        return membresPermanents;
    }

    public void setCaisseASavon(CaisseASavon caisseASavon) {
        this.caisseASavon = caisseASavon;
    }

    public CaisseASavon getCaisseASavon() {
        return caisseASavon;
    }

    //Autres méthodes ---------------------------------------------------------------------

    public void ajouterMembre(Membre membre){
        this.membresPermanents.add(membre);
    }

    public void supprimerMembre(Membre membre){
        this.membresPermanents.remove(membre);
    }
}
