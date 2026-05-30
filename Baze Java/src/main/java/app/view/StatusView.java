package app.view;

import app.Connect;
import app.classes.Eksperiment;
import app.controller.Eksperimentcnt;
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

import java.sql.*;

public class StatusView extends BorderPane {
    private Label lblNaslov;
    private TableView<Eksperiment> tv;
    private TextField tf;
    private Button btnConfirm;
    private Hyperlink link;
    private HBox linija;
    private VBox linija2;

    public StatusView() {
        initElements();
        addElements();
        addActions();
        loadInfos();
        Platform.runLater(() -> tv.requestFocus());
    }

    private void initElements() {
        lblNaslov = new Label("Eksperimenti");
        tf = new TextField();
        btnConfirm = new Button("Potvrdi");
        linija = new HBox(tf, btnConfirm);
        linija.setAlignment(Pos.CENTER);
        link = new Hyperlink("Nazad");
        linija2 = new VBox(linija, link);
        linija2.setAlignment(Pos.CENTER);
        tv = new TableView<Eksperiment>();
        tv.setPadding(new Insets(10));
        tv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tv.setOnMousePressed(e -> {
            tv.requestFocus();
            e.consume();
        });

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
        naslovi.setAlignment(Pos.CENTER);
        naslovi.setPadding(new Insets(20));
        this.setPadding(new Insets(20));
        linija.setAlignment(Pos.CENTER);
        linija.setSpacing(20);
        linija2.setAlignment(Pos.CENTER);
        linija2.setSpacing(10);
        linija2.setPadding(new Insets(20));
        this.setBottom(linija2);
    }

    private void addActions() {
        link.setOnAction(new Home());
        btnConfirm.setOnAction(btnClick());
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
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                list.add(new Eksperiment(rs.getInt("id_eksperiment"), rs.getString("tip"), rs.getString("naziv"), rs.getString("status")));
            }
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tv.setItems(list);
    }

    private EventHandler<ActionEvent> btnClick() {
        return e -> {
            ObservableList<Eksperiment> select = tv.getSelectionModel().getSelectedItems();
            String status = tf.getText();

            String sql = "UPDATE Izvodjenje SET status = ? WHERE id_eksperiment = ?";

            try {
                Connection con = Connect.getKonekcija();
                PreparedStatement ps = con.prepareStatement(sql);
                for (Eksperiment eksperiment : select) {
                    ps.setString(1, status);
                    ps.setInt(2, eksperiment.getId());
                    ps.executeUpdate();
                }
                con.close();
                loadInfos();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
    }
}
