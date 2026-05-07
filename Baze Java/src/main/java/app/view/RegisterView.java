package app.view;

import app.controller.Home;
import app.controller.Login;
import app.controller.SignUp;
import app.controller.SignUpSave;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RegisterView extends BorderPane {

    Label naslov;
    Label logovanje;
    TextField tflogovanje;
    PasswordField pflogovanje;
    Button btnlogovanje;

    public RegisterView() {
        initElements();
        addElements();
        addActions();
    }

    private void initElements() {
        naslov = new Label("Registracija");
        naslov.setFont(Font.font("Arial", 30));
        logovanje = new Label("Unesite korisnicko ime i lozinku");
        tflogovanje = new TextField();
        pflogovanje = new PasswordField();
        btnlogovanje = new Button("Registruj se");
    }

    private void addElements() {
        setPadding(new Insets(40));
        setAlignment(naslov, Pos.CENTER);
        setTop(naslov);

        HBox hb = new HBox(btnlogovanje);
        hb.setAlignment(Pos.CENTER);
        VBox vb = new VBox(logovanje, tflogovanje, pflogovanje, hb);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.setMaxWidth(200);

        setCenter(vb);
    }

    private void addActions() {
       // btnlogovanje.setOnAction(new Home(this));
        btnlogovanje.setOnAction(new SignUpSave(this));
    }

    public TextField getTflogovanje() {
        return tflogovanje;
    }

    public PasswordField getPflogovanje() {
        return pflogovanje;
    }
}
