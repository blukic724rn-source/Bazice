package app.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Eksperiment {
    private IntegerProperty id;
    private StringProperty tip;
    private StringProperty naziv;
    private StringProperty status;

    public Eksperiment(int id, String tip, String naziv, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.tip = new SimpleStringProperty(tip);
        this.naziv = new SimpleStringProperty(naziv);
        this.status = new SimpleStringProperty(status);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTip() {
        return tip.get();
    }

    public StringProperty tipProperty() {
        return tip;
    }

    public String getNaziv() {
        return naziv.get();
    }

    public StringProperty nazivProperty() {
        return naziv;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
}
