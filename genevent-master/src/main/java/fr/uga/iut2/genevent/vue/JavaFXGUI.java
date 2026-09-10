package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import fr.uga.iut2.genevent.modele.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * La classe JavaFXGUI est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique.
 * <p>
 * Attention, pour pouvoir faire le lien avec le
 * {@link fr.uga.iut2.genevent.controleur.Controleur}, JavaFXGUI n'est pas une
 * sous-classe de {@link javafx.application.Application} !
 * <p>
 * Le démarrage de l'application diffère des exemples classiques trouvés dans
 * la documentation de JavaFX : l'interface est démarrée à l'initiative du
 * {@link fr.uga.iut2.genevent.controleur.Controleur} via l'appel de la méthode
 * {@link #demarrerInteraction()}.
 */
public class JavaFXGUI extends IHM {

    private final Controleur controleur;
    private final CountDownLatch eolBarrier;  // /!\ ne pas supprimer /!\ : suivi de la durée de vie de l'interface

    // éléments vue nouvel·le utilisa·teur/trice
    @FXML private TextField newUserForenameTextField;
    @FXML private TextField newUserSurnameTextField;
    @FXML private TextField newUserEmailTextField;
    @FXML private Button newUserOkButton;
    @FXML private Button newUserCancelButton;

    // page connexion
    @FXML private TextField loginConnexion;
    @FXML private TextField passwordConnexion;
    @FXML private Label infoLabelConnexion;
    @FXML private Button createAccountButton;
    @FXML private Button seConnecterButton;
    // page creation compte
    @FXML private TextField loginFieldCreerCompte;
    @FXML private PasswordField mdpFieldCreer;
    @FXML private PasswordField mdpConfirmFieldCreer;
    @FXML private Label infoCreerCompteLabel;
    @FXML private Button retourConnexionButton;
    // page accueil
    @FXML private Button creerCourseButton;
    @FXML private Button creerEquipeButton;

    @FXML private TableView<TableauCourses> tableCourse;
    @FXML private TableColumn<TableauCourses, String> columnNomCourse;
    @FXML private TableColumn<TableauCourses, Integer> columnNombreEquipes;
    @FXML private TableColumn<TableauCourses, Integer> columnBenefice;

    @FXML private TableView<TableauEquipes> tableEquipe;
    @FXML private TableColumn<TableauEquipes, String> columnNomEquipe;
    @FXML private TableColumn<TableauEquipes, Integer> columnNombreCourses;

    @FXML private TableView<TableauGains> tableGain;
    @FXML private TableColumn<TableauGains, String> columnNomCourseGains;
    @FXML private TableColumn<TableauGains, String> columnDateDebut;
    @FXML private TableColumn<TableauGains, Float> columnRevenuCourse;
    @FXML private TableColumn<TableauGains, Float> columnDepenseCourse;
    @FXML private Button voirPlusButtonGains;
    @FXML private Button voirPlusButtonCourses;
    @FXML private Button voirPlusButtonEquipes;
    @FXML private TextField textFieldRevenuTotal;
    @FXML private TextField textFieldDepenseTotal;
    @FXML private TextField textFieldNombreCourseTotal;


    // page creer course
    @FXML private Button sauvegarderCourse;
    @FXML private TextField nomCourseField;
    @FXML private DatePicker dateDebutPicker;
    @FXML private DatePicker dateFinPicker;
    @FXML private TextField lieuCourseField;
    @FXML private TextArea descriptionCourseArea;
    @FXML private TextField chercherEquipeField;
    @FXML private VBox listeEquipePageCourse;
    @FXML private Button creerEquipeButtonPageCourse;
    @FXML private Spinner<Double> revenusFieldCourse;
    @FXML private Spinner<Double> coutsFieldCourse;
    @FXML private Label labelNomCourse;
    @FXML private Label labelDateDebut;
    @FXML private Label labelDateFin;
    @FXML private Label labelRevenusCourse;
    @FXML private Label labelCoutsCourse;
    // page creer equipe
    @FXML private TextField nomEquipeField;
    @FXML private Button sauvegarderEquipeButton;
    @FXML private TextField themeCaisseField;
    @FXML private Spinner<Integer> poidsCaisseField;
    @FXML private VBox listeMembresCreerEquipe;
    @FXML private Label labelMembreEquipe;
    @FXML private Label labelNomEquipe;
    // page compte
    @FXML private Button accueilButtonPageCompte;
    // page Info course
    @FXML private Button accueilButtonPageInfoCourse;
    @FXML private TextField nomCourseFieldInfoCourse;
    @FXML private TextField localisationFieldInfoCourse;
    @FXML private TextArea descriptionAreaInfoCourse;
    @FXML private TextField revenuFieldInfoCourse;
    @FXML private TextField depenseFieldInfoCourse;
    @FXML private TableView<TableauEquipesInfoCourse> tableauEquipeInfoCourse;
    @FXML private TableColumn<TableauEquipesInfoCourse, String> columnNomEquipeInfoCourse;
    @FXML private TableColumn<TableauEquipesInfoCourse, Integer> columnNombreMembresInfoCourse;
    // page info equipe
    @FXML private Button accueilButtonPageInfoEquipe;
    @FXML private TextField nomEquipeFieldInfoEquipe;
    @FXML private TextField themeCaisseFieldInfoEquipe;
    @FXML private TextField poidsCaisseFieldInfoEquipe;
    @FXML private TableView<TableauMembresInfoEquipe> tableauMembresInfoEquipe;
    @FXML private TableColumn<TableauMembresInfoEquipe, String> columnNomEtPrenomInfoEquipe;
    @FXML private TableView<TableauParticipationInfoEquipe> tableauParticipationInfoEquipe;
    @FXML private TableColumn<TableauParticipationInfoEquipe, String> colmunNomCourseInfoEquipe;
    // page recherche course
    @FXML private Button accueilButtonRechercheCourse;
    @FXML private VBox ListeCoursePageRechercheCourse;
    @FXML private TextField chercherRechecheCourseField;
    //page recherche equipe
    @FXML private Button accueilButtonRechercheEquipe;
    @FXML private VBox ListeEquipePageRechercheEquipe;
    @FXML private TextField chercherRechecheEquipeField;


    public JavaFXGUI(Controleur controleur) {
        this.controleur = controleur;

        this.eolBarrier = new CountDownLatch(1);  // /!\ ne pas supprimer /!\
    }

    /**
     * Point d'entrée principal pour le code de l'interface JavaFX.
     *
     * @param primaryStage stage principale de l'interface JavaFX, sur laquelle
     *     définir des scenes.
     *
     * @throws IOException si le chargement de la vue FXML échoue.
     *
     * @see javafx.application.Application#start(Stage)
     */
    private void start(Stage primaryStage) throws IOException {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("ConnexionCompte.fxml"));
        mainViewLoader.setController(this);
        Scene mainScene = new Scene(mainViewLoader.load());

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Picture/Gèr-TaCaisse_Caisse_carre.png")));
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("Gèr'TaCaisse");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // méthodes init (pour afficher une page avec les informations actualisées)
    private void initAccueil(Button button) throws IOException {
        FXMLLoader accueilLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        accueilLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(accueilLoader.load()));
        stage.show();

        columnNomCourse.setCellValueFactory(cellData -> cellData.getValue().nomCourseProperty());
        columnNombreEquipes.setCellValueFactory(cellData -> cellData.getValue().nombreEquipesProperty().asObject());
        columnBenefice.setCellValueFactory(cellData -> cellData.getValue().beneficeProperty().asObject());

        columnNomEquipe.setCellValueFactory(cellData -> cellData.getValue().nomEquipeProperty());
        columnNombreCourses.setCellValueFactory(cellData -> cellData.getValue().nombreCoursesProperty().asObject());

        columnNomCourseGains.setCellValueFactory(cellData -> cellData.getValue().nomCourseProperty());
        columnDateDebut.setCellValueFactory(cellData -> cellData.getValue().dateCourseProperty());
        columnRevenuCourse.setCellValueFactory(cellData -> cellData.getValue().revenuCourseProperty().asObject());
        columnDepenseCourse.setCellValueFactory(cellData -> cellData.getValue().depenseCourseProperty().asObject());



        int RT = 0;
        int DT = 0;


        for (Course c : controleur.genevent.getEvenements().values()) {
            RT += (int) c.getRevenu();
            DT += (int) c.getCout();
        }
        String RevenuTotal = Integer.toString(RT);
        textFieldRevenuTotal.setText(RevenuTotal);



        String DepenseTotal = Integer.toString(DT);
        textFieldDepenseTotal.setText(DepenseTotal);

        int NBR = controleur.genevent.getEvenements().size();
        String NombreCourseTotal = Integer.toString(NBR);
        textFieldNombreCourseTotal.setText(NombreCourseTotal);


        chargerDonnees();
    }

    private void initCreerCourse(Button button, Course course) throws IOException {
        FXMLLoader creerCourseLoader = new FXMLLoader(getClass().getResource("CreerCourse.fxml"));
        creerCourseLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(creerCourseLoader.load()));
        stage.show();

        actualiseListeEquipePageCourse("");
        revenusFieldCourse.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0));
        coutsFieldCourse.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0));

        nomCourseField.setText(course.getNom());
        dateDebutPicker.setValue(course.getDateDebut());
        dateFinPicker.setValue(course.getDateFin());
        lieuCourseField.setText(course.getLieu());
        descriptionCourseArea.setText(course.getDescription());
        revenusFieldCourse.getValueFactory().setValue((double) course.getRevenu());
        coutsFieldCourse.getValueFactory().setValue((double) course.getCout());
        for (Node hbox : listeEquipePageCourse.getChildren()) {
            ArrayList<Equipe> equipes = course.getEquipes();
            String nomEquipeCourant = ((Label) ((HBox) hbox).getChildren().get(1)).getText();
            boolean existe = equipes.stream().anyMatch(e -> e.getNom().equals(nomEquipeCourant));
            if (existe) {
                ((CheckBox) ((HBox) hbox).getChildren().get(0)).setSelected(true);
            }
        }
    }

    private void initCreerEquipe(Button button, Equipe equipe) throws IOException {
        FXMLLoader creerEquipeLoader = new FXMLLoader(getClass().getResource("CreerEquipe.fxml"));
        creerEquipeLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(creerEquipeLoader.load()));
        stage.show();

        poidsCaisseField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
        HBox first = (HBox) listeMembresCreerEquipe.getChildren().get(0);
        TextField nom = (TextField) first.getChildren().get(1);
        TextField prenom = (TextField) first.getChildren().get(2);
        nom.setOnKeyTyped(e -> retirerBordureRouge(nom));
        prenom.setOnKeyTyped(e -> retirerBordureRouge(prenom));

        nomEquipeField.setText(equipe.getNom());
        themeCaisseField.setText(equipe.getCaisseASavon().getTheme());
        poidsCaisseField.getValueFactory().setValue(equipe.getCaisseASavon().getPoids());
    }

    private void initRechercheEquipe(Button button) throws IOException {
        FXMLLoader rechercheEquipeLoader = new FXMLLoader(getClass().getResource("RechercheEquipe.fxml"));
        rechercheEquipeLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(rechercheEquipeLoader.load()));
        stage.show();

        actualiseListeEquipePageRechercheEquipe("");
    }

    private void initPageInfoEquipe(Button button, Equipe equipe) throws IOException {
        FXMLLoader pageInfoEquipeLoader = new FXMLLoader(getClass().getResource("PageInfoEquipe.fxml"));
        pageInfoEquipeLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(pageInfoEquipeLoader.load()));
        stage.show();

        nomEquipeFieldInfoEquipe.setText(equipe.getNom());
        themeCaisseFieldInfoEquipe.setText(equipe.getCaisseASavon().getTheme());
        poidsCaisseFieldInfoEquipe.setText(Integer.toString(equipe.getCaisseASavon().getPoids()));

        columnNomEtPrenomInfoEquipe.setCellValueFactory(cellData -> cellData.getValue().nomEtPrenomProperty());
        colmunNomCourseInfoEquipe.setCellValueFactory(cellData -> cellData.getValue().nomCourseProperty());

        ObservableList<TableauMembresInfoEquipe> membres = FXCollections.observableArrayList();

        for (int i = 0; i < equipe.getMembresPermanents().size(); i++) {
            membres.add(new TableauMembresInfoEquipe(
                    equipe.getMembresPermanents().get(i).getNom()
                    + " "
                    + equipe.getMembresPermanents().get(i).getPrenom()
            ));
        }
        tableauMembresInfoEquipe.setItems(membres);


        ObservableList<TableauParticipationInfoEquipe> participations = FXCollections.observableArrayList();

        for (Map.Entry<String, Course> entry : controleur.genevent.getEvenements().entrySet()) {
            Course courseCourrante = controleur.genevent.getEvenements().get(entry.getKey());
            if (courseCourrante.getEquipes().contains(equipe)) {
                participations.add(new TableauParticipationInfoEquipe(courseCourrante.getNom()));
            }
        }
        tableauParticipationInfoEquipe.setItems(participations);
    }

    private void initRechercheCourse(Button button) throws IOException {
        FXMLLoader rechercheCourseLoader = new FXMLLoader(getClass().getResource("RechercheCourse.fxml"));
        rechercheCourseLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(rechercheCourseLoader.load()));
        stage.show();

        actualiseListeCoursePageRechercheCourse("");
    }

    private void initPageInfoCourse(Button button, Course course) throws IOException {
        FXMLLoader pageInfoCourseLoader = new FXMLLoader(getClass().getResource("PageInfoCourse.fxml"));
        pageInfoCourseLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(pageInfoCourseLoader.load()));
        stage.show();

        nomCourseFieldInfoCourse.setText(course.getNom());
        localisationFieldInfoCourse.setText(course.getLieu());
        descriptionAreaInfoCourse.setText(course.getDescription());
        revenuFieldInfoCourse.setText(Float.toString(course.getRevenu()));
        depenseFieldInfoCourse.setText(Float.toString(course.getCout()));

        columnNomEquipeInfoCourse.setCellValueFactory(cellData -> cellData.getValue().nomEquipeProperty());
        columnNombreMembresInfoCourse.setCellValueFactory(cellData -> cellData.getValue().nombreMembresProperty().asObject());

        ObservableList<TableauEquipesInfoCourse> equipes = FXCollections.observableArrayList();

        for (int i = 0; i < course.getEquipes().size(); i++) {
            equipes.add(new TableauEquipesInfoCourse(course.getEquipes().get(i).getNom(), course.getEquipes().get(i).getMembresPermanents().size()));
        }
        tableauEquipeInfoCourse.setItems(equipes);
    }

    private void initPageCompte(Button button) throws IOException {
        FXMLLoader pageCompteLoader = new FXMLLoader(getClass().getResource("PageCompte.fxml"));
        pageCompteLoader.setController(this);

        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(pageCompteLoader.load()));
        stage.show();
    }

    // méthodes pour l'interface ConnexionCompte
    @FXML
    private void onConnexion() throws IOException {
        if (loginConnexion.getText().isEmpty() || passwordConnexion.getText().isEmpty()) {
            infoLabelConnexion.setTextFill(Color.RED);
        } else {
            initAccueil(seConnecterButton);
        }
    }

    @FXML
    private void actualiseInfoConnexion() {
        infoLabelConnexion.setTextFill(Color.BLACK);
    }

    @FXML
    private void onCreateAccount() throws IOException {
        FXMLLoader creationCompteLoader = new FXMLLoader(getClass().getResource("CreationCompte.fxml"));
        creationCompteLoader.setController(this);

        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        stage.setScene(new Scene(creationCompteLoader.load()));
        stage.show();
    }

    // méthodes pour l'interface CreationCompte
    @FXML
    private void onRetourPageConnexion() throws IOException {
        FXMLLoader connexionCompteLoader = new FXMLLoader(getClass().getResource("ConnexionCompte.fxml"));
        connexionCompteLoader.setController(this);

        Stage stage = (Stage) retourConnexionButton.getScene().getWindow();
        stage.setScene(new Scene(connexionCompteLoader.load()));
        stage.show();
    }

    @FXML
    private void onCreerCompte() throws IOException {
        if (loginFieldCreerCompte.getText().isEmpty() || mdpFieldCreer.getText().isEmpty() || mdpConfirmFieldCreer.getText().isEmpty()) {
            infoCreerCompteLabel.setTextFill(Color.RED);
        } else if (!mdpFieldCreer.getText().equals(mdpConfirmFieldCreer.getText())) {
            infoCreerCompteLabel.setText("Les mots de passe ne correspondent pas.");
            infoCreerCompteLabel.setTextFill(Color.RED);
        } else if (!loginFieldCreerCompte.getText().isEmpty() && !mdpFieldCreer.getText().isEmpty()) {
            onRetourPageConnexion();
        }
    }

    @FXML
    private void actualiseLabelCreerCompte() {
        infoCreerCompteLabel.setText("Tous les champs sont obligatoires.");
        infoCreerCompteLabel.setTextFill(Color.BLACK);
    }

    // méthodes pour l'interface Accueil
    @FXML
    private void onCreerCourse() throws IOException {
        initCreerCourse(creerCourseButton, new Course(controleur.genevent, "", null, null));
    }

    @FXML
    private void onCreerEquipe() throws IOException {
        initCreerEquipe(creerEquipeButton, new Equipe(controleur.genevent, ""));
    }

    @FXML
    private void onVoirPlusGains() throws IOException {
        initPageCompte(voirPlusButtonGains);
    }

    @FXML
    private void onVoirPlusCourses() throws IOException {
        initRechercheCourse(voirPlusButtonCourses);
    }

    @FXML
    private void onVoirPlusEquipes() throws IOException {
        initRechercheEquipe(voirPlusButtonEquipes);
    }

    // méthodes pour l'interface CreerCourse
    @FXML
    private void onSauvegarderCourse() throws IOException {
        if (!nomCourseField.getText().isEmpty() & validerDatesCourse() & validerSpinnerRevenu() & validerSpinnerCouts()) {
            Course c = new Course(controleur.genevent, nomCourseField.getText(), dateDebutPicker.getValue(), dateFinPicker.getValue());
            controleur.genevent.getEvenements().put(nomCourseField.getText(), c);
            c.setLieu(lieuCourseField.getText());
            c.setDescription(descriptionCourseArea.getText());
            c.setFinie(false);
            c.setRevenu(revenusFieldCourse.getValue().floatValue());
            c.setCout(coutsFieldCourse.getValue().floatValue());

            // pour chaque équipe (checkbox) sélectionnée, je l'ajoute dans la course avec course.ajouterEquipe(equipe)
            if (!listeEquipePageCourse.getChildren().isEmpty()) {
                for (Node hbox : listeEquipePageCourse.getChildren()) {
                    String key = ((Label) ((HBox) hbox).getChildren().get(1)).getText();
                    if (((CheckBox) ((HBox) hbox).getChildren().get(0)).isSelected()) {
                        c.ajouterEquipe(controleur.genevent.getEquipes().get(key));
                    } else {
                        c.supprimerEquipe(controleur.genevent.getEquipes().get(key));
                    }
                }
            }

            initAccueil(sauvegarderCourse);
            informerUtilisateur("Course « " + c.getNom() + " » sauvegardée.", true);
        } else {
            if (nomCourseField.getText().isEmpty()) {
                labelNomCourse.setTextFill(Color.RED);
            }
        }
    }
    private boolean validerDatesCourse() {
        boolean valide = true;

        LocalDate debut = dateDebutPicker.getValue();
        LocalDate fin   = dateFinPicker.getValue();

        if (debut == null) {
            labelDateDebut.setTextFill(Color.RED);
            valide = false;
        }
        if (fin == null) {
            labelDateFin.setTextFill(Color.RED);
            valide = false;
        }
        if (debut != null && fin != null && debut.isAfter(fin)) {
            labelDateDebut.setTextFill(Color.RED);
            labelDateFin.setTextFill(Color.RED);
            valide = false;
        }

        return valide;
    }
    private boolean validerSpinnerRevenu() {
        String texte = revenusFieldCourse.getEditor().getText().trim();

        if (texte.isEmpty()) {
            labelRevenusCourse.setTextFill(Color.RED);
            return false;
        }

        double valeur;
        try {
            valeur = Double.parseDouble(texte.replace(",", "."));
        } catch (NumberFormatException e) {
            labelRevenusCourse.setTextFill(Color.RED);
            return false;
        }

        SpinnerValueFactory.DoubleSpinnerValueFactory factory =
                (SpinnerValueFactory.DoubleSpinnerValueFactory) revenusFieldCourse.getValueFactory();

        if (valeur < factory.getMin() || valeur > factory.getMax()) {
            labelRevenusCourse.setTextFill(Color.RED);
            return false;
        }

        labelRevenusCourse.setTextFill(Paint.valueOf("#2C3E50"));
        factory.setValue(valeur);
        return true;
    }
    private boolean validerSpinnerCouts() {
        String texte = coutsFieldCourse.getEditor().getText().trim();

        if (texte.isEmpty()) {
            labelCoutsCourse.setTextFill(Color.RED);
            return false;
        }

        double valeur;
        try {
            valeur = Double.parseDouble(texte.replace(",", "."));
        } catch (NumberFormatException e) {
            labelCoutsCourse.setTextFill(Color.RED);
            return false;
        }

        SpinnerValueFactory.DoubleSpinnerValueFactory factory =
                (SpinnerValueFactory.DoubleSpinnerValueFactory) coutsFieldCourse.getValueFactory();

        if (valeur < factory.getMin() || valeur > factory.getMax()) {
            labelCoutsCourse.setTextFill(Color.RED);
            return false;
        }

        labelCoutsCourse.setTextFill(Paint.valueOf("#2C3E50"));
        factory.setValue(valeur);
        return true;
    }

    @FXML
    private void actualiseLabelDates() {
        if (dateDebutPicker.getValue() != null) labelDateDebut.setTextFill(Paint.valueOf("#2C3E50"));
        if (dateFinPicker.getValue() != null) labelDateFin.setTextFill(Paint.valueOf("#2C3E50"));
    }

    @FXML
    private void actualiseConditionNomCreerCourse() {
        labelNomCourse.setTextFill(Color.valueOf("#2C3E50"));
    }

    @FXML
    private void onCreerEquipePageCourse() throws IOException {
        FXMLLoader creerEquipeLoader = new FXMLLoader(getClass().getResource("CreerEquipe.fxml"));
        creerEquipeLoader.setController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(creerEquipeLoader.load()));
        stage.show();

        poidsCaisseField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1));
        HBox first = (HBox) listeMembresCreerEquipe.getChildren().get(0);
        TextField nom = (TextField) first.getChildren().get(1);
        TextField prenom = (TextField) first.getChildren().get(2);
        nom.setOnKeyTyped(e -> retirerBordureRouge(nom));
        prenom.setOnKeyTyped(e -> retirerBordureRouge(prenom));


        sauvegarderEquipeButton.setOnAction(e -> {
            TextField nom2 = (TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(0)).getChildren().get(1);
            TextField prenom2 = (TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(0)).getChildren().get(2);
            if (!nomEquipeField.getText().isBlank() && !nom2.getText().isBlank() && !prenom2.getText().isBlank()) {
                Equipe e2 = new Equipe(controleur.genevent, nomEquipeField.getText());
                CaisseASavon caisse = new CaisseASavon(themeCaisseField.getText(), poidsCaisseField.getValue());
                e2.setCaisseASavon(caisse);
                controleur.genevent.getEquipes().put(nomEquipeField.getText(), e2);

                for (int i = 0; i < listeMembresCreerEquipe.getChildren().size(); i++) {
                    String nomActuel = ((TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(i)).getChildren().get(1)).getText();
                    String prenomActuel = ((TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(i)).getChildren().get(2)).getText();
                    if (!nomActuel.isBlank() && !prenomActuel.isBlank()) {
                        e2.ajouterMembre(new Membre(nomActuel, prenomActuel));
                    }
                }

                actualiseListeEquipePageCourse(chercherEquipeField.getText());
                stage.close();
            }
        });
    }

    @FXML
    private void chercherEquipePageCourse() {
        actualiseListeEquipePageCourse(chercherEquipeField.getText());
    }

    private void actualiseListeEquipePageCourse(String search) {
        listeEquipePageCourse.getChildren().clear();

        boolean boobackground = false;

        Insets paddingHbox = new Insets(10);

        for (String nomEquipe : controleur.genevent.getEquipes().keySet()) {
            if (nomEquipe.toLowerCase().contains(search.toLowerCase().trim())) {
                HBox equipe = new HBox(new CheckBox(), new Label(nomEquipe));
                equipe.setSpacing(20);
                equipe.setPadding(paddingHbox);

                if (boobackground)
                {
                    equipe.setStyle("-fx-background-color: #E0F5FF");
                    boobackground = false;
                }
                else {boobackground = true;}

                listeEquipePageCourse.getChildren().add(equipe);
            }
        }

        listeEquipePageCourse.setSpacing(10);
    }

    @FXML
    private void chercherCoursePageRechercheCourse() {
        actualiseListeCoursePageRechercheCourse(chercherRechecheCourseField.getText());
    }

    private void actualiseListeCoursePageRechercheCourse(String search) {
        ListeCoursePageRechercheCourse.getChildren().clear();

        boolean boobackground = false;

        for (String nomCourse : controleur.genevent.getEvenements().keySet()) {

            Insets paddingHbox = new Insets(10);
            Label label = new Label(nomCourse);
            label.setStyle("-fx-font-size: 17px;");

            Pane pane = new Pane();
            pane.prefHeight(200);
            pane.prefWidth(200);
            HBox.setHgrow(pane, Priority.ALWAYS);

            Button info = new Button("Info");
            info.setStyle("-fx-background-color: #00B6D4; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");
            info.setCursor(Cursor.HAND);
            info.setOnAction(e -> {
                try {
                    initPageInfoCourse(info, controleur.genevent.getEvenements().get(label.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Button modifier = new Button("Modifier");
            modifier.setStyle("-fx-background-color: #00B6D4; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");
            modifier.setCursor(Cursor.HAND);
            modifier.setOnAction(e -> {
                try {
                    initCreerCourse(modifier, controleur.genevent.getEvenements().get(label.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Button supprimer = new Button("Supprimer");
            supprimer.setStyle("-fx-background-color: #fdebf0; -fx-text-fill: red; -fx-background-radius: 5; -fx-border-radius: 5;");
            supprimer.setCursor(Cursor.HAND);
            supprimer.setOnAction(e -> confirmerSupressionCourse(controleur.genevent.getEvenements().get(label.getText())));

            if (nomCourse.toLowerCase().contains(search.toLowerCase().trim())) {
                HBox course = new HBox(label,pane, info, modifier, supprimer);
                course.setSpacing(20);
                course.setPadding(paddingHbox);

                if (boobackground)
                {
                    course.setStyle("-fx-background-color: #E0F5FF");
                    boobackground = false;
                }
                else {boobackground = true;}

                ListeCoursePageRechercheCourse.getChildren().add(course);
            }

        }

        ListeCoursePageRechercheCourse.setSpacing(10);
    }

    @FXML
    private void chercherEquipePageRechercheEquipe() {
        actualiseListeEquipePageRechercheEquipe(chercherRechecheEquipeField.getText());
    }

    private void actualiseListeEquipePageRechercheEquipe(String search) {
        ListeEquipePageRechercheEquipe.getChildren().clear();

        boolean boobackground = false;

        for (String nomEquipe : controleur.genevent.getEquipes().keySet()) {

            Insets paddingHbox = new Insets(10);
            Label label = new Label(nomEquipe);
            label.setStyle("-fx-font-size: 17px;");

            Pane pane = new Pane();
            pane.prefHeight(200);
            pane.prefWidth(200);
            HBox.setHgrow(pane, Priority.ALWAYS);

            Button info = new Button("Info");
            info.setStyle("-fx-background-color: #00B6D4; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");
            info.setCursor(Cursor.HAND);
            info.setOnAction(e -> {
                try {
                    initPageInfoEquipe(info, controleur.genevent.getEquipes().get(label.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Button modifier = new Button("Modifier");
            modifier.setStyle("-fx-background-color: #00B6D4; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");
            modifier.setCursor(Cursor.HAND);
            modifier.setOnAction(e -> {
                try {
                    initCreerEquipe(modifier, controleur.genevent.getEquipes().get(label.getText()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            Button supprimer = new Button("Supprimer");
            supprimer.setStyle("-fx-background-color: #fdebf0; -fx-text-fill: red; -fx-background-radius: 5; -fx-border-radius: 5;");
            supprimer.setCursor(Cursor.HAND);
            supprimer.setOnAction(e -> confirmerSuppressionEquipe(controleur.genevent.getEquipes().get(label.getText())));

            if (nomEquipe.toLowerCase().contains(search.toLowerCase().trim())) {
                HBox course = new HBox(label,pane, info, modifier, supprimer);
                course.setSpacing(20);
                course.setPadding(paddingHbox);

                if (boobackground)
                {
                    course.setStyle("-fx-background-color: #E0F5FF");
                    boobackground = false;
                }
                else {boobackground = true;}

                ListeEquipePageRechercheEquipe.getChildren().add(course);
            }

        }

        ListeEquipePageRechercheEquipe.setSpacing(10);
    }


    @FXML
    private void onAccueilButtonPageCourse() throws IOException {
        initAccueil(sauvegarderCourse);
    }

    // méthodes pour l'interface CreerEquipe
    @FXML
    private void onSauvegarderEquipe() throws IOException {
        TextField nom = (TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(0)).getChildren().get(1);
        TextField prenom = (TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(0)).getChildren().get(2);
        if (!nomEquipeField.getText().isBlank() & validerMembreEquipe(nom, prenom)) {
            Equipe e = new Equipe(controleur.genevent, nomEquipeField.getText());
            CaisseASavon caisse = new CaisseASavon(themeCaisseField.getText(), poidsCaisseField.getValue());
            e.setCaisseASavon(caisse);
            controleur.genevent.getEquipes().put(nomEquipeField.getText(), e);

            for (int i = 0; i < listeMembresCreerEquipe.getChildren().size(); i++) {
                String nomActuel = ((TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(i)).getChildren().get(1)).getText();
                String prenomActuel = ((TextField) ((HBox) listeMembresCreerEquipe.getChildren().get(i)).getChildren().get(2)).getText();
                if (!nomActuel.isBlank() && !prenomActuel.isBlank()) {
                    e.ajouterMembre(new Membre(nomActuel, prenomActuel));
                }
            }

            initAccueil(sauvegarderEquipeButton);
            informerUtilisateur("Équipe « " + e.getNom() + " » sauvegardée.", true);
        }
        if (nomEquipeField.getText().isEmpty()) {
            labelNomEquipe.setTextFill(Color.RED);
        }
    }
    private boolean validerMembreEquipe(TextField nom, TextField prenom) {
        if (nom.getText().isBlank()) {
            labelMembreEquipe.setTextFill(Color.RED);
            return false;
        }
        if (prenom.getText().isBlank()) {
            labelMembreEquipe.setTextFill(Color.RED);
            return false;
        }
        labelMembreEquipe.setTextFill(Color.BLACK);
        return true;
    }

    @FXML
    private void actualiseConditionsCreerEquipe() {
        labelNomEquipe.setTextFill(Color.BLACK);
    }

    @FXML
    private void onAjouterMembre() {
        HBox derniereLigne = (HBox) listeMembresCreerEquipe.getChildren().get(listeMembresCreerEquipe.getChildren().size() - 1);
        TextField nomDernier = (TextField) derniereLigne.getChildren().get(1);
        TextField prenomDernier = (TextField) derniereLigne.getChildren().get(2);
        if (nomDernier.getText().isBlank() || prenomDernier.getText().isBlank()) {
            if (nomDernier.getText().isBlank()) {
                nomDernier.setStyle("-fx-border-color: red;");
            }
            if (prenomDernier.getText().isBlank()) {
                prenomDernier.setStyle("-fx-border-color: red;");
            }
        } else {
            listeMembresCreerEquipe.getChildren().add(creerMembreHBox(listeMembresCreerEquipe.getChildren().size() + 1));
        }
    }

    private HBox creerMembreHBox(int num) {
        TextField nom = new TextField();
        nom.setOnKeyTyped(e -> retirerBordureRouge(nom));
        nom.setPromptText("Nom");

        TextField prenom = new TextField();
        prenom.setOnKeyTyped(e -> retirerBordureRouge(prenom));
        prenom.setPromptText("Prénom");

        Button delete = new Button("X");
        delete.setOnAction(e -> listeMembresCreerEquipe.getChildren().remove(delete.getParent()));

        Label label = new Label("Membre " + num);
        HBox hbox = new HBox(10,
            label,
            nom,
            prenom,
            delete
        );

        hbox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        hbox.setPrefSize(400, 35);
        label.setPrefWidth(80);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        nom.setPrefWidth(100);
        nom.setStyle("-fx-border-color: #0bc5e3;");
        prenom.setPrefWidth(100);
        prenom.setStyle("-fx-border-color: #0bc5e3;");

        return hbox;
    }

    @FXML
    private void onAccueilButtonPageEquipe() throws IOException {
        initAccueil(sauvegarderEquipeButton);
    }

    // méthodes pour l'interface PageCompte
    @FXML
    private void onAccueilButtonPageCompte() throws IOException {
        initAccueil(accueilButtonPageCompte);
    }

    // méthodes pour l'interface PageInfoCourse
    @FXML
    private void onAccueilButtonPageInfoCourse() throws IOException {
        initAccueil(accueilButtonPageInfoCourse);
    }
    @FXML private void onRetourPageInfoCourse() throws IOException {
        initRechercheCourse(accueilButtonPageInfoCourse);
    }
    private void confirmerSupressionCourse(Course course) {
        Insets paddingbox = new Insets(10);
        Insets paddingBigbox = new Insets(50);

        Stage stage = new Stage();

        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> stage.close());
        annuler.setStyle("-fx-background-color: #00B6D4; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e -> {
            controleur.genevent.getEvenements().remove(course.getNom());
            actualiseListeCoursePageRechercheCourse(chercherRechecheCourseField.getText());
            stage.close();
        });
        supprimer.setStyle("-fx-background-color: #fdebf0; -fx-text-fill: red; -fx-background-radius: 5; -fx-border-radius: 5;");

        HBox hbox = new HBox(annuler, supprimer);
        hbox.setSpacing(20);
        hbox.setPadding(paddingbox);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(new Label("Voulez-vous vraiment supprimer la course ?"));

        VBox Bigvbox = new VBox(hbox,vbox);
        Bigvbox.setSpacing(20);
        Bigvbox.setPadding(paddingBigbox);
        Bigvbox.setAlignment(Pos.CENTER);
        Bigvbox.setStyle("-fx-background-color: #E0F5FF");

        Scene scene = new Scene(Bigvbox);

        stage.setScene(scene);
        stage.show();
    }


    // méthodes pour l'interface PageInfoEquipe
    @FXML
    private void onAccueilButtonPageInfoEquipe() throws IOException {
        initAccueil(accueilButtonPageInfoEquipe);
    }
    @FXML
    private void onRetourButtonPageInfoEquipe() throws IOException {
        initRechercheEquipe(accueilButtonPageInfoEquipe);
    }
    private void confirmerSuppressionEquipe(Equipe equipe) {
        Insets paddingbox = new Insets(10);
        Insets paddingBigbox = new Insets(50);

        Stage stage = new Stage();

        Button annuler = new Button("Annuler");
        annuler.setOnAction(e -> stage.close());
        annuler.setStyle("-fx-background-color: #00B6D4; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");

        Button supprimer = new Button("Supprimer");
        supprimer.setOnAction(e -> {
            controleur.genevent.getEquipes().remove(equipe.getNom());
            actualiseListeEquipePageRechercheEquipe(chercherRechecheEquipeField.getText());
            stage.close();
        });
        supprimer.setStyle("-fx-background-color: #fdebf0; -fx-text-fill: red; -fx-background-radius: 5; -fx-border-radius: 5;");

        HBox hbox = new HBox(annuler, supprimer);
        hbox.setSpacing(20);
        hbox.setPadding(paddingbox);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(new Label("Voulez-vous vraiment supprimer l'équipe ?"));

        VBox Bigvbox = new VBox(hbox,vbox);
        Bigvbox.setSpacing(20);
        Bigvbox.setPadding(paddingBigbox);
        Bigvbox.setAlignment(Pos.CENTER);
        Bigvbox.setStyle("-fx-background-color: #E0F5FF");

        Scene scene = new Scene(Bigvbox);

        stage.setScene(scene);
        stage.show();
    }

    // méthodes pour l'interface RechercheCourse
    @FXML
    private void onAccueilButtonRechercheCourse() throws IOException {
        initAccueil(accueilButtonRechercheCourse);
    }

    // méthodes pour l'interface RechercheEquipe() {
    @FXML
    private void onAccueilButtonRechercheEquipe() throws IOException {
        initAccueil(accueilButtonRechercheEquipe);
    }

    // méthodes communes
    private void retirerBordureRouge(TextField t) {
        t.setStyle("-fx-border-color: #0bc5e3;");
    }

    private void chargerDonnees() {
        ObservableList<TableauCourses> courses = FXCollections.observableArrayList();

        for (String key : controleur.genevent.getEvenements().keySet()) {
            Course c = controleur.genevent.getEvenements().get(key);
            courses.add(new TableauCourses(c.getNom(), c.getEquipes().size(), (int) (c.getRevenu()-c.getCout())));
        }
        tableCourse.setItems(courses);


        ObservableList<TableauEquipes> equipes = FXCollections.observableArrayList();

        int nombresCourses;
        for(String key1 : controleur.genevent.getEquipes().keySet()) {
            Equipe e = controleur.genevent.getEquipes().get(key1);
            nombresCourses = 0;
            for (String key2 : controleur.genevent.getEvenements().keySet()) {
                for (Equipe eq : controleur.genevent.getEvenements().get(key2).getEquipes()) {
                    if(Objects.equals(eq.getNom(), e.getNom()))
                        nombresCourses += 1;
                }
            }
            equipes.add(new TableauEquipes(e.getNom(), nombresCourses));
        }
        tableEquipe.setItems(equipes);


        ObservableList<TableauGains> gains = FXCollections.observableArrayList();

        for (String key3 : controleur.genevent.getEvenements().keySet()) {
            Course c = controleur.genevent.getEvenements().get(key3);
            gains.add(new TableauGains(c.getNom(), c.getDateDebut(), c.getRevenu(), c.getCout()));
        }
        tableGain.setItems(gains);
    }

//-----  Éléments du dialogue  -------------------------------------------------

    private void exitAction() {
        // fermeture de l'interface JavaFX : on notifie sa fin de vie
        this.eolBarrier.countDown();
    }

    // menu principal  -----

    @FXML
    private void newUserMenuItemAction() {
        this.controleur.saisirUtilisateur();
    }

    @FXML
    private void exitMenuItemAction() {
        Platform.exit();
        this.exitAction();
    }

    // vue nouvel·le utilisa·teur/trice  -----

    @FXML
    private void createNewUserAction() {
        IHM.InfosUtilisateur data = new IHM.InfosUtilisateur(
                this.newUserEmailTextField.getText().strip().toLowerCase(),
                this.newUserSurnameTextField.getText().strip(),
                this.newUserForenameTextField.getText().strip()
        );
        this.controleur.creerUtilisateur(data);
        this.newUserOkButton.getScene().getWindow().hide();
    }

    @FXML
    private void cancelNewUserAction() {
        this.newUserCancelButton.getScene().getWindow().hide();
    }

    @FXML
    private void validateTextFields() {
        boolean isValid = true;

        isValid &= validateNonEmptyTextField(this.newUserForenameTextField);
        isValid &= validateNonEmptyTextField(this.newUserSurnameTextField);
        isValid &= validateEmailTextField(this.newUserEmailTextField);

        this.newUserOkButton.setDisable(!isValid);
    }

    private static void markTextFieldErrorStatus(TextField textField, boolean isValid) {
        if (isValid) {
            textField.setStyle(null);
        } else {
            textField.setStyle("-fx-control-inner-background: f8d7da");
        }
    }

    private static boolean validateNonEmptyTextField(TextField textField) {
        boolean isValid = !textField.getText().isBlank();

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

    private static boolean validateEmailTextField(TextField textField) {
        EmailValidator validator = EmailValidator.getInstance(false, false);
        boolean isValid = validator.isValid(textField.getText().strip().toLowerCase());

        markTextFieldErrorStatus(textField, isValid);

        return isValid;
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void demarrerInteraction() {
        // démarrage de l'interface JavaFX
        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            primaryStage.setOnCloseRequest((WindowEvent t) -> this.exitAction());
            try {
                this.start(primaryStage);
            }
            catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        });

        // attente de la fin de vie de l'interface JavaFX
        try {
            this.eolBarrier.await();
        }
        catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }

    @Override
    public void informerUtilisateur(String msg, boolean succes) {
        final Alert alert = new Alert(
                succes ? Alert.AlertType.INFORMATION : Alert.AlertType.WARNING
        );
        alert.setTitle("GenEvent");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void saisirUtilisateur() {
        try {
            FXMLLoader newUserViewLoader = new FXMLLoader(getClass().getResource("new-user-view.fxml"));
            newUserViewLoader.setController(this);
            Scene newUserScene = new Scene(newUserViewLoader.load());

            Stage newUserWindow = new Stage();
            newUserWindow.setTitle("Créer un·e utilisa·teur/trice");
            newUserWindow.initModality(Modality.APPLICATION_MODAL);
            newUserWindow.setScene(newUserScene);
            newUserWindow.showAndWait();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void saisirNouvelEvenement(Set<String> nomsExistants) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
