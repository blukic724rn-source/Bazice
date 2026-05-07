package app.controller;

import app.App;
import app.view.HomeView;
import app.view.RegisterView;
import javafx.event.EventHandler;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.*;

public class SignUpSave implements EventHandler<ActionEvent> {
    private RegisterView registerView;

    public SignUpSave(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void handle(ActionEvent event) {
        String korisnik = registerView.getTflogovanje().getText();
        String lozinka = registerView.getPflogovanje().getText();
        boolean postoji = false;

        try (BufferedReader br = new BufferedReader(new FileReader("usernames.txt"))) {

            String linija;

            while ((linija = br.readLine()) != null) {

                String[] dijelovi = linija.split(",");

                if (dijelovi[0].equals(korisnik)) {
                    postoji = true;
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (postoji) {
            System.out.println("Korisnicko ime vec postoji!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Korisnicko ime je zauzeto!");
            alert.show();
            return;
        }

        String podaciZaUpis = korisnik + "," + lozinka;

        try (FileWriter fw = new FileWriter("usernames.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(podaciZaUpis);
            System.out.println("Podaci su uspesno sacuvani u fajl!");

        } catch (IOException e) {
            System.out.println("Doslo je do greske prilikom upisa u fajl.");
            e.printStackTrace();
        }

        System.out.println("Prozoric");

        App.window.close();
        App.window = new Stage();
        App.window.setScene(new Scene(new HomeView(), 500, 700));
        App.window.show();
    }
}
