package ba.unsa.etf.rpr.tutorijal09;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {
    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private SimpleStringProperty naziv = new SimpleStringProperty("");
    private Grad glavniGrad;

    public Drzava() {
    }

    public Drzava(int id, String naziv, int glavnGrad) {
        this.id = new SimpleIntegerProperty(id);
        this.naziv = new SimpleStringProperty(naziv);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }


    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }

}
