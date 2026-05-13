package app.controller;

import app.App;
import app.view.EksperimentiView;
import app.view.StatusView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Statuscnt implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        App.window.close();
        App.window = new Stage();
        App.window.setScene(new Scene(new StatusView(), 500, 500));
        App.window.show();
    }
}
