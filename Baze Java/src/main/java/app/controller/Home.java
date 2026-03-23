package app.controller;

import app.App;
import app.view.HomeView;
import app.view.RegisterView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Home implements EventHandler<ActionEvent> {
    private RegisterView registerView;

    public Home(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println("Prozoric");

        App.window.close();
        App.window = new Stage();
        App.window.setScene(new Scene(new HomeView(), 500, 700));
        App.window.show();
    }
}
