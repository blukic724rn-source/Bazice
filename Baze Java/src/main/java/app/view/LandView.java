package app.view;

import app.controller.Login;
import app.controller.SignUp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LandView extends BorderPane {
    Label naslov;
    Label logovanje;
    TextField tflogovanje;
    PasswordField pflogovanje;
    Button btnlogovanje;
    Hyperlink link;

    public LandView() {
        initElements();
        addElements();
        addActions();
    }

    private void initElements() {
        naslov = new Label("Pocetna stranica");
        naslov.setFont(Font.font("Arial", 30));
        logovanje = new Label("Ulogujte se");
        tflogovanje = new TextField();
        pflogovanje = new PasswordField();
        btnlogovanje = new Button("Uloguj se");
        link = new Hyperlink("Nemate nalog?");
    }

    private void addElements() {
        setPadding(new Insets(40));
        setAlignment(naslov, Pos.CENTER);
        setTop(naslov);

        HBox hb = new HBox(btnlogovanje, link);
        hb.setAlignment(Pos.CENTER);
        VBox vb = new VBox(logovanje, tflogovanje, pflogovanje, hb);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.setMaxWidth(200);

        setCenter(vb);
    }

    private void addActions() {
        btnlogovanje.setOnAction(new Login(this));
        link.setOnAction(new SignUp(this));
    }

    public TextField getTflogovanje() {
        return tflogovanje;
    }

    public void setTflogovanje(TextField tflogovanje) {
        this.tflogovanje = tflogovanje;
    }

    public PasswordField getPflogovanje() {
        return pflogovanje;
    }

    public Hyperlink getLink() {
        return link;
    }
}
