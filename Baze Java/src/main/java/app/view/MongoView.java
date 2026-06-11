package app.view;

import app.Connect;
import app.MongoConnection;
import app.classes.Eksperiment;
import app.controller.Home;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import org.bson.Document;
import java.sql.*;

public class MongoView extends BorderPane {
    private Label lblNaslov;
    private TableView<Eksperiment> tv;
    private TextArea taDetalji;
    private Hyperlink link;

    public MongoView() {
        initElements();
        addElements();
        addActions();
        loadEksperimenti();
    }

    private void initElements() {
        lblNaslov = new Label("Uspesno zavrseni eksperimenti");
        link = new Hyperlink("Nazad");

        tv = new TableView<>();
        TableColumn<Eksperiment, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Eksperiment, String> colNaziv = new TableColumn<>("Naziv");
        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        TableColumn<Eksperiment, String> colTip = new TableColumn<>("Tip");
        colTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
        tv.getColumns().addAll(colId, colNaziv, colTip);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        taDetalji = new TextArea();
        taDetalji.setEditable(false);
        taDetalji.setPromptText("Klikni na eksperiment za detalje...");
        taDetalji.setPrefWidth(300);
    }

    private void addElements() {
        HBox top = new HBox(lblNaslov);
        top.setAlignment(Pos.CENTER);
        top.setPadding(new Insets(20));
        this.setTop(top);
        this.setCenter(tv);
        this.setRight(taDetalji);
        this.setBottom(link);
        this.setPadding(new Insets(10));
    }

    private void addActions() {
        link.setOnAction(new Home());
        tv.setOnMouseClicked(e -> {
            Eksperiment selected = tv.getSelectionModel().getSelectedItem();
            if (selected == null) return;
            ucitajDetalje(selected.getId());
        });
    }

    private void loadEksperimenti() {
        ObservableList<Eksperiment> list = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT e.id_eksperiment, e.naziv, e.tip " +
                "FROM Eksperiment e " +
                "JOIN Izvodjenje i ON e.id_eksperiment = i.id_eksperiment " +
                "WHERE i.status = 'zavrseno_uspesno'";
        try {
            Connection con = Connect.getKonekcija();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Eksperiment(
                        rs.getInt("id_eksperiment"),
                        rs.getString("tip"),
                        rs.getString("naziv"),
                        "zavrseno_uspesno"
                ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv.setItems(list);
    }

    private void ucitajDetalje(int idEksperimenta) {
        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> collection = db.getCollection("rezultati");

        Document doc = collection.find(
                new Document("id_eksperiment", idEksperimenta)
        ).first();

        if (doc == null) {
            taDetalji.setText("Nema podataka u MongoDB za ovaj eksperiment.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== EKSPERIMENT ").append(idEksperimenta).append(" ===\n\n");
        sb.append("Naziv: ").append(doc.getString("naziv")).append("\n");
        sb.append("Tip: ").append(doc.getString("tip")).append("\n\n");

        Document kval = (Document) doc.get("kvalitativni_rezultati");
        if (kval != null) {
            sb.append("--- Kvalitativni rezultati ---\n");
            sb.append("Zakljucak: ").append(kval.getString("zakljucak")).append("\n");
            sb.append("Kvalitet podataka: ").append(kval.getString("kvalitet_podataka")).append("\n");
            sb.append("Anomalije: ").append(kval.getString("anomalije")).append("\n\n");
        }

        Document kvant = (Document) doc.get("kvantitativni_rezultati");
        if (kvant != null) {
            sb.append("--- Kvantitativni rezultati ---\n");
            sb.append("Tacnost merenja: ").append(kvant.getString("tacnost_merenja")).append("\n");
            sb.append("Broj uzoraka: ").append(kvant.getInteger("broj_uzoraka")).append("\n");
            sb.append("Std devijacija: ").append(kvant.getDouble("standardna_devijacija")).append("\n");
            sb.append("Prosecna vrednost: ").append(kvant.getDouble("prosecna_vrednost")).append("\n");
        }

        taDetalji.setText(sb.toString());
    }
}
