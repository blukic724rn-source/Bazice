package app.controller;

import app.App;
import app.view.HomeView;
import app.view.LandView;
import app.view.RegisterView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SignUp implements EventHandler<ActionEvent> {
    private LandView landView;

    public SignUp(LandView landView) {
        this.landView = landView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Prozorcic");
        App.window.close();
        App.window = new Stage();
        App.window.setScene(new Scene(new RegisterView(), 500, 700));
        App.window.show();
    }
}
