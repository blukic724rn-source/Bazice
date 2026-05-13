package app.view;

import app.Connect;
import app.classes.Eksperiment;
import app.classes.Sesija;
import app.controller.Home;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SesijaView extends BorderPane {
    private Label lblNaslov;
    private TableView<Sesija> tv;
    private Button btnDelete;
    private Hyperlink link;

    public SesijaView() {
        initElements();
        addElements();
        addActions();
        loadInfos();
        Platform.runLater(() -> tv.requestFocus());
    }

    private void initElements() {
        lblNaslov = new Label("Sesije");
        tv = new TableView<Sesija>();
        tv.setPadding(new Insets(10));
        tv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv.setOnMousePressed(e -> {
            tv.requestFocus();
            e.consume();
        });

        TableColumn<Sesija, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Sesija, String> columnDate1 = new TableColumn<>("Datum pocetka");
        columnDate1.setCellValueFactory(new PropertyValueFactory<>("datum_pocetka"));

        TableColumn<Sesija, String> columnDate2 = new TableColumn<>("Datum zavrsetka");
        columnDate2.setCellValueFactory(new PropertyValueFactory<>("datum_zavrsetka"));

        TableColumn<Sesija, Integer> columnData = new TableColumn<>("Kolicina podataka");
        columnData.setCellValueFactory(new PropertyValueFactory<>("kolicina_podataka"));

        TableColumn<Sesija, Integer> columnHourProc = new TableColumn<>("Procesorski sati");
        columnHourProc.setCellValueFactory(new PropertyValueFactory<>("procesorski_sati"));

        tv.getColumns().addAll(columnId, columnDate1, columnDate2, columnData, columnHourProc);

        btnDelete = new Button("Obrisi!");
        link = new Hyperlink("Nazad");
    }

    private void addElements() {
        HBox naslovi = new HBox(lblNaslov);
        this.setTop(naslovi);
        this.setCenter(tv);
        VBox vb = new VBox(btnDelete, link);
        this.setBottom(vb);
        this.setPadding(new Insets(10));
        naslovi.setAlignment(Pos.CENTER);
        naslovi.setPadding(new Insets(20));
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10));
    }

    private void addActions() {
        btnDelete.setOnAction(btnClick());
        link.setOnAction(new Home());
    }

    private EventHandler<ActionEvent> btnClick() {
        return e -> {
            ObservableList<Sesija> select = tv.getSelectionModel().getSelectedItems();

            String sql = "DELETE FROM Sesija WHERE id_sesija = ?";

            try {
                Connection con = Connect.getKonekcija();
                PreparedStatement ps = con.prepareStatement(sql);
                for (Sesija s : select) {
                    ps.setInt(1, s.getId());
                    ps.executeUpdate();
                }
                con.close();
                loadInfos();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }

    private void loadInfos() {

        ObservableList<Sesija> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Sesija";
        try {
            Connection con = Connect.getKonekcija();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                list.add(new Sesija(rs.getInt("id_sesija"), rs.getString("datum_pocetka"), rs.getString("datum_zavrsetka"), rs.getInt("kolicina_podataka"), rs.getInt("procesorski_sati")));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tv.setItems(list);
    }
}
