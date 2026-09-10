package fr.uga.iut2.genevent.modele;

import javafx.beans.property.SimpleStringProperty;

public class TableauMembresInfoEquipe {
    private SimpleStringProperty nomEtPrenom;

    public TableauMembresInfoEquipe(String nomEtPrenom) {
        this.nomEtPrenom = new SimpleStringProperty(nomEtPrenom);
    }

    public String getNomEtPrenom() {
        return nomEtPrenom.get();
    }
    public SimpleStringProperty nomEtPrenomProperty() {
        return nomEtPrenom;
    }
}
