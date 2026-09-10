package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public class Membre implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nom;
    private String prenom;
    private Role role;
    private boolean estPermanent;

    public Membre(String nom, String prenom) {
        setNom(nom);
        setPrenom(prenom);
    }

    public Membre(String nom, String prenom, Role role, boolean estPermanent) {
        new Membre(nom, prenom);
        setRole(role);
        setPermanent(estPermanent);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.trim();
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom.trim();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean estPermanent() {
        return estPermanent;
    }

    public void setPermanent(boolean estPermanent) {
        this.estPermanent = estPermanent;
    }
}
