package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Course implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private static ArrayList<String> noms = new ArrayList<>();

    private final GenEvent genevent;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String lieu;
    private String description;
    private float revenu;
    private boolean estFinie;
    private float cout;
    private ArrayList<Equipe> equipes;
    private final Map<String, Utilisateur> administrateurs;  // association qualifiée par l'email

    // Invariant de classe : !dateDebut.isAfter(dateFin)
    //     On utilise la négation ici pour exprimer (dateDebut <= dateFin), ce
    //     qui est équivalent à !(dateDebut > dateFin).

    public static Course initialiseCourse(GenEvent genevent, String nom, LocalDate dateDebut, LocalDate dateFin, Utilisateur admin) {
        Course course = new Course(genevent, nom, dateDebut, dateFin);
        course.ajouteAdministrateur(admin);
        return course;
    }

    public Course(GenEvent genevent, String nom, LocalDate dateDebut, LocalDate dateFin) {
        if (noms.contains(nom)) {
            System.out.println("Une course avec ce nom existe déjà, veillez choisir un autre nom.");
        }

        assert !dateDebut.isAfter(dateFin);
        this.genevent = genevent;
        setNom(nom);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        this.lieu = "";
        this.description = "";
        this.revenu = 0;
        this.estFinie = false;
        this.cout = 0;
        this.equipes = new ArrayList<>();
        this.administrateurs = new HashMap<>();
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom.trim();
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        assert !dateDebut.isAfter(this.dateFin);
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        assert !this.dateDebut.isAfter(dateFin);
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    public void ajouterEquipe(Equipe equipe) {
        equipes.add(equipe);
    }

    public void supprimerEquipe(Equipe equipe) {
        equipes.remove(equipe);
    }

    public float getRevenu() {
        return revenu;
    }

    public void setRevenu(float revenu) {
        this.revenu = revenu;
    }

    public boolean estFinie() {
        return estFinie;
    }

    public void setFinie(boolean estFinie) {
        this.estFinie = estFinie;
    }

    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        if (cout >= 0) {
            this.cout = cout;
        }
    }

    public void ajouteAdministrateur(Utilisateur admin) {
        assert !this.administrateurs.containsKey(admin.getEmail());
        this.administrateurs.put(admin.getEmail(), admin);
        admin.ajouteEvenementAdministre(this);
    }
}
