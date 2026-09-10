package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableauEquipesInfoCourse {

    private SimpleStringProperty nomEquipe;
    private SimpleIntegerProperty nombreMembres;

    public TableauEquipesInfoCourse(String nomEquipe, int nombreMembres) {
        this.nomEquipe = new SimpleStringProperty(nomEquipe);
        this.nombreMembres = new SimpleIntegerProperty(nombreMembres);
    }

    public String getNomEquipe() {
        return nomEquipe.get();
    }
    public SimpleStringProperty nomEquipeProperty() {
        return nomEquipe;
    }

    public int getNombreMembres() {
        return nombreMembres.get();
    }
    public SimpleIntegerProperty nombreMembresProperty() {
        return nombreMembres;
    }
}
