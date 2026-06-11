package app.controller;

import app.Connect;
import app.MongoConnection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class MongoInsert {
    public static void main(String[] args) throws Exception {
        Connection con = Connect.getKonekcija();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT DISTINCT e.id_eksperiment, e.naziv, e.tip " +
                        "FROM Eksperiment e " +
                        "JOIN Izvodjenje i ON e.id_eksperiment = i.id_eksperiment " +
                        "WHERE i.status = 'zavrseno_uspesno'"
        );

        MongoDatabase db = MongoConnection.getDatabase();
        MongoCollection<Document> collection = db.getCollection("rezultati");

        while (rs.next()) {
            Document doc = new Document()
                    .append("id_eksperiment", rs.getInt("id_eksperiment"))
                    .append("naziv", rs.getString("naziv"))
                    .append("tip", rs.getString("tip"))
                    .append("kvalitativni_rezultati", new Document()
                            .append("zakljucak", "Eksperiment uspesno sproveden")
                            .append("kvalitet_podataka", "visok")
                            .append("anomalije", "nije detektovano")
                    )
                    .append("kvantitativni_rezultati", new Document()
                            .append("tacnost_merenja", Math.round(Math.random() * 20 + 80) + "%")
                            .append("broj_uzoraka", (int)(Math.random() * 500 + 100))
                            .append("standardna_devijacija", Math.round(Math.random() * 100) / 100.0)
                            .append("prosecna_vrednost", Math.round(Math.random() * 1000) / 10.0)
                    )
                    .append("oprema", Arrays.asList("Teleskop", "Spektrograf", "CCD Kamera"))
                    .append("datum_analize", new java.util.Date());

            collection.insertOne(doc);
            System.out.println("Dodat eksperiment: " + rs.getInt("id_eksperiment"));
        }
        con.close();
        System.out.println("Gotovo!");
    }
}
