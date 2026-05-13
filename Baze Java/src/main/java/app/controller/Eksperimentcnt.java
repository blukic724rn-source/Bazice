package app.controller;

import app.App;
import app.view.EksperimentiView;
import app.view.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Eksperimentcnt implements EventHandler<ActionEvent> {
    private HomeView home;

    public Eksperimentcnt (HomeView h) {
        this.home = h;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        App.window.close();
        App.window = new Stage();
        App.window.setScene(new Scene(new EksperimentiView(), 500, 500));
        App.window.show();
    }
}
