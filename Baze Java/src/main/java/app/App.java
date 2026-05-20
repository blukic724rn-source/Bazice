package app;

//import app.utils.FileUtil;
import app.view.HomeView;
import app.view.LandView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;

public class App extends Application {

    public static Stage window;
    public static int id_usern;

    @Override
    public void start(Stage stage) throws Exception {
        Connection con = Connect.getKonekcija();
        System.out.println("Povezana");
        con.close();
        window = stage;
        window.setScene(new Scene(new LandView(), 400, 400));
        window.show();
    }
}
