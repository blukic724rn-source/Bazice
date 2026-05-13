package app.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sesija {
    private IntegerProperty id;
    private StringProperty datum_pocetka;
    private StringProperty datum_zavrsetka;
    private IntegerProperty kolicina_podataka;
    private IntegerProperty procesorski_sati;
    private IntegerProperty id_alat;
    private IntegerProperty id_izvodjenje;

    public Sesija(int id, String datum_pocetka, String datum_zavrsetka, int kolicina_podataka, int procesorski_sati, int id_alat, int id_izvodjenje) {
        this.id = new SimpleIntegerProperty(id);
        this.datum_pocetka = new SimpleStringProperty(datum_pocetka);
        this.datum_zavrsetka = new SimpleStringProperty(datum_zavrsetka);
        this.kolicina_podataka = new SimpleIntegerProperty(kolicina_podataka);
        this.procesorski_sati = new SimpleIntegerProperty(procesorski_sati);
        this.id_alat = new SimpleIntegerProperty(id_alat);
        this.id_izvodjenje = new SimpleIntegerProperty(id_izvodjenje);
    }

    public Sesija(int id, String datum_pocetka, String datum_zavrsetka, int kolicina_podataka, int procesorski_sati) {
        this.id = new SimpleIntegerProperty(id);
        this.datum_pocetka = new SimpleStringProperty(datum_pocetka);
        this.datum_zavrsetka = new SimpleStringProperty(datum_zavrsetka);
        this.kolicina_podataka = new SimpleIntegerProperty(kolicina_podataka);
        this.procesorski_sati = new SimpleIntegerProperty(procesorski_sati);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getDatum_pocetka() {
        return datum_pocetka.get();
    }

    public StringProperty datum_pocetkaProperty() {
        return datum_pocetka;
    }

    public String getDatum_zavrsetka() {
        return datum_zavrsetka.get();
    }

    public StringProperty datum_zavrsetkaProperty() {
        return datum_zavrsetka;
    }

    public int getKolicina_podataka() {
        return kolicina_podataka.get();
    }

    public IntegerProperty kolicina_podatakaProperty() {
        return kolicina_podataka;
    }

    public int getProcesorski_sati() {
        return procesorski_sati.get();
    }

    public IntegerProperty procesorski_satiProperty() {
        return procesorski_sati;
    }

    public int getId_alat() {
        return id_alat.get();
    }

    public IntegerProperty id_alatProperty() {
        return id_alat;
    }

    public int getId_izvodjenje() {
        return id_izvodjenje.get();
    }

    public IntegerProperty id_izvodjenjeProperty() {
        return id_izvodjenje;
    }
}
