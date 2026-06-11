package app.view;

import app.controller.Eksperimentcnt;
import app.controller.MongoCnt;
import app.controller.Sesijacnt;
import app.controller.Statuscnt;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeView extends BorderPane {
    private Label lblNaslov;
    private Button btnEksperimenti;
    private Button btnEksperimenti2;
    private Button btnSesija;
    private HBox hbox;
    private Button btnMongo;

    public HomeView() {
        initElements();
        addElemenets();
        addActions();
    }

    private void initElements() {
        lblNaslov = new Label("Home");
        btnEksperimenti = new Button("Forma 1");
        btnEksperimenti2 = new Button("Forma 2");
        btnSesija = new Button("Forma 3");
        btnMongo = new Button("Forma 4");
        hbox = new HBox(btnEksperimenti, btnEksperimenti2, btnSesija, btnMongo);
    }

    private void addElemenets() {
        HBox hb = new HBox(lblNaslov);
        this.setTop(hb);
        this.setPadding(new Insets(50));
        hb.setAlignment(Pos.CENTER);
        this.setCenter(hbox);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
    }

    private void addActions() {
        btnEksperimenti.setOnAction(new Eksperimentcnt(this));
        btnEksperimenti2.setOnAction(new Statuscnt());
        btnSesija.setOnAction(new Sesijacnt());
        btnMongo.setOnAction(new MongoCnt());
    }
}
