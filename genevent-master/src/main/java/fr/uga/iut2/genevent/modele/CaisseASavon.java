package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public class CaisseASavon implements Serializable {
    private static final long serialVersionUID = 1L;

    private String theme;
    private int poids;

    public CaisseASavon(String theme, int poids) {
        setPoids(poids);
        setTheme(theme);
    }

    public String getTheme(){
        return theme;
    }

    public void setTheme(String theme){
        this.theme = theme.trim();
    }

    public int getPoids(){
        return poids;
    }

    public void setPoids(int poids) {
        if (poids < 1) {
            this.poids = 1;
        } else if (poids > 100) {
            this.poids = 100;
        } else {
            this.poids = poids;
        }
    }
}
