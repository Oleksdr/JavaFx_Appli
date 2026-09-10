package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TableauGains {

    private SimpleStringProperty nomCourse;
    private SimpleStringProperty dateCourse;
    private SimpleFloatProperty revenuCourse;
    private SimpleFloatProperty depenseCourse;

    public TableauGains(String nomCourse, LocalDate dateCourse, float revenuCourse, float depenseCourse) {
        this.nomCourse = new SimpleStringProperty(nomCourse);
        this.dateCourse = new SimpleStringProperty(dateCourse.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.revenuCourse = new SimpleFloatProperty(revenuCourse);
        this.depenseCourse = new SimpleFloatProperty(depenseCourse);
    }

    public String getNomCourse() {
        return nomCourse.get();
    }
    public SimpleStringProperty nomCourseProperty() {
        return nomCourse;
    }

    public String getDateCourse() {
        return dateCourse.get();
    }
    public SimpleStringProperty dateCourseProperty() {
        return dateCourse;
    }

    public float getRevenuCourse() {
        return revenuCourse.get();
    }
    public SimpleFloatProperty revenuCourseProperty() {
        return revenuCourse;
    }

    public float getDepenseCourse() {
        return depenseCourse.get();
    }
    public SimpleFloatProperty depenseCourseProperty() {
        return depenseCourse;
    }
}
