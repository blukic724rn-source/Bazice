package app.controller;

import app.App;
import app.view.MongoView;
import app.view.StatusView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MongoCnt implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        App.window.close();
        App.window = new Stage();
        App.window.setScene(new Scene(new MongoView(), 500, 500));
        App.window.show();
    }
}
