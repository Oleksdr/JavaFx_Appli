package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableauCourses {
    private SimpleStringProperty nomCourse;
    private SimpleIntegerProperty nombreEquipes;
    private SimpleIntegerProperty benefice;

    public TableauCourses(String nomCourse, int nombreEquipes, int benefice) {
        this.nomCourse = new SimpleStringProperty(nomCourse);
        this.nombreEquipes = new SimpleIntegerProperty(nombreEquipes);
        this.benefice = new SimpleIntegerProperty(benefice);
    }

    public String getNomCourse() {
        return nomCourse.get();
    }
    public SimpleStringProperty nomCourseProperty() {
        return nomCourse;
    }

    public int getNombreEquipes() {
        return nombreEquipes.get();
    }
    public SimpleIntegerProperty nombreEquipesProperty() {
        return nombreEquipes;
    }

    public int getBenefice() {
        return benefice.get();
    }
    public SimpleIntegerProperty beneficeProperty() {
        return benefice;
    }
}
