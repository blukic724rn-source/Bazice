package app.controller;

import app.App;
import app.view.HomeView;
import app.view.LandView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login implements EventHandler<ActionEvent> {
    private LandView landView;

    public Login(LandView landView) {
        this.landView = landView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String korisnik = landView.getTflogovanje().getText();
        String lozinka = landView.getPflogovanje().getText();

        if(korisnik == "") {
            System.out.println("Nema ime");
        } else if (lozinka == "") {
            System.out.println("Nema lozinke");
        } else {
            System.out.println("Prozorce");

            App.window.close();
            App.window = new Stage();
            App.window.setScene(new Scene(new HomeView(), 500, 700));
            App.window.show();
        }
    }
}
