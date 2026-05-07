package app.controller;

import app.App;
import app.view.HomeView;
import app.view.LandView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;

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
            return;
        } else if (lozinka == "") {
            System.out.println("Nema lozinke");
            return;
        }

        boolean ulogovan = false;

        try (FileReader fr = new FileReader("usernames.txt");
             BufferedReader br = new BufferedReader(fr)) {

            String linija;
            while ((linija = br.readLine()) != null) {
                String[] podaci = linija.split(",");

                if (podaci.length == 2) {
                    String sacuvaniKorisnik = podaci[0];
                    String sacuvanaLozinka = podaci[1];
                    if (korisnik.equals(sacuvaniKorisnik) && lozinka.equals(sacuvanaLozinka)) {
                        ulogovan = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Doslo je do greske prilikom citanja fajla");
            e.printStackTrace();
        }

        if (ulogovan) {
            System.out.println("Ulogovan");
            App.window.close();
            App.window = new Stage();
            App.window.setScene(new Scene(new HomeView(), 500, 700));
            App.window.show();
        } else {
            System.out.println("Pogresno korisnicko ime ili lozinka!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Pogresno korisnicko ime ili lozinka!");
            alert.show();
        }
    }
}
