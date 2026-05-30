package app.view;

import app.Connect;
import app.classes.Eksperiment;
import app.controller.Home;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;

public class EksperimentiView extends BorderPane {
    private Label lblNaslov;
    private TableView tv;
    private Button btnZavrseni;
    private Button btnPlanirani;
    private HBox linija;
    private Hyperlink link;
    private VBox linija2;

    public EksperimentiView() {
        initelements();
        addElements();
        addActions();
        loadInfos();
    }

    private void initelements() {
        lblNaslov = new Label("Eksperimenti");
        btnPlanirani = new Button("Planirani");
        btnZavrseni = new Button("Zavrseni");
        link = new Hyperlink("Nazad");
        linija = new HBox(btnZavrseni, btnPlanirani);
        linija2 = new VBox(linija, link);
        tv = new TableView<Eksperiment>();
        tv.setPadding(new Insets(10));

        TableColumn<Eksperiment, Integer> columnId = new TableColumn<>("ID");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Eksperiment, String> columnNaziv = new TableColumn<>("Naziv");
        columnNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));

        TableColumn<Eksperiment, String> columnTip = new TableColumn<>("Tip");
        columnTip.setCellValueFactory(new PropertyValueFactory<>("tip"));

        TableColumn<Eksperiment, String> columnStatus = new TableColumn<>("Status");
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tv.getColumns().addAll(columnId, columnNaziv, columnTip, columnStatus);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    private void addElements() {
        HBox naslovi = new HBox(lblNaslov);
        this.setTop(naslovi);
        this.setCenter(tv);
        linija.setAlignment(Pos.CENTER);
        linija.setSpacing(20);
        linija2.setAlignment(Pos.CENTER);
        linija2.setSpacing(10);
        linija2.setPadding(new Insets(20));
        this.setBottom(linija2);
        naslovi.setAlignment(Pos.CENTER);
        naslovi.setPadding(new Insets(20));

        this.setPadding(new Insets(20));
    }

    private void addActions() {
        btnZavrseni.setOnAction(e -> ucitaj("zavrseno_uspesno"));
        btnPlanirani.setOnAction(e -> ucitaj("planirano"));
        link.setOnAction(new Home());
    }

    private void ucitaj(String stat) {
        ObservableList<Eksperiment> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Eksperiment e JOIN Izvodjenje i ON e.id_eksperiment = i.id_eksperiment WHERE i.status = ?";

        try {
            Connection con = Connect.getKonekcija();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, stat);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Eksperiment(rs.getInt("id_eksperiment"), rs.getString("tip"), rs.getString("naziv"), rs.getString("status")));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tv.setItems(list);
    }

    private void loadInfos() {
        ObservableList<Eksperiment> list = FXCollections.observableArrayList();
        String sql = "SELECT e.id_eksperiment, e.naziv, e.tip, " + "i.status " + "FROM Eksperiment e " +
                "JOIN Izvodjenje i ON e.id_eksperiment = i.id_eksperiment " +
                "WHERE i.id_izvodjenje = (" +
                "SELECT MAX(id_izvodjenje) FROM Izvodjenje " +
                "WHERE id_eksperiment = e.id_eksperiment)";
        try {
            Connection con = Connect.getKonekcija();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Eksperiment(rs.getInt("id_eksperiment"), rs.getString("tip"), rs.getString("naziv"), rs.getString("status")));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tv.setItems(list);
    }
}
