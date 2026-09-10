package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableauEquipes {

    private SimpleStringProperty nomEquipe;
    private SimpleIntegerProperty nombreCourses;

    public TableauEquipes(String nomEquipe, int nombreCourses){
        this.nomEquipe = new SimpleStringProperty(nomEquipe);
        this.nombreCourses = new SimpleIntegerProperty(nombreCourses);
    }

    public String getNomEquipe() {
        return nomEquipe.get();
    }
    public SimpleStringProperty nomEquipeProperty() { return nomEquipe; }

    public int getNombreCourses() {
        return nombreCourses.get();
    }
    public SimpleIntegerProperty nombreCoursesProperty() { return nombreCourses; }


}
