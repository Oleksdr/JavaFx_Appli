package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleStringProperty;

public class TableauParticipationInfoEquipe {
    private SimpleStringProperty nomCourse;

    public TableauParticipationInfoEquipe(String nomCourse) {
        this.nomCourse = new SimpleStringProperty(nomCourse);
    }

    public String getNomCourse() {
        return nomCourse.get();
    }
    public SimpleStringProperty nomCourseProperty() {
        return nomCourse;
    }
}
