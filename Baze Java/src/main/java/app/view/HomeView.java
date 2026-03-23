package app.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeView extends BorderPane {
    private Label lblNaslov;

    public HomeView() {
        initElements();
        addElemenets();
        addActions();
    }

    private void initElements() {
        lblNaslov = new Label("Home");
    }

    private void addElemenets() {
        HBox hb = new HBox(lblNaslov);
        this.setTop(hb);
        this.setPadding(new Insets(50));
        hb.setAlignment(Pos.CENTER);
    }

    private void addActions() {
    }
}
