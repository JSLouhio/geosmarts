package geosmarts.geosmarts.Tietokantataulut;

import java.util.ArrayList;

public class Alue {

    private int id;
    private String nimi;
    private ArrayList<Viestiketju> viestiketjut;

    public Alue(int id, String n) {
        this.id=id;
        this.nimi=n;
        this.viestiketjut = new ArrayList();
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setNimi(String i) {
        this.nimi = i;
    }

    public void lisaaViesti(Viestiketju v) {
        this.viestiketjut.add(v);
    }

    public int getId() {
        return this.id;
    }

    public String getNimi() {
        return this.nimi;
    }
    
    public ArrayList<Viestiketju> getKetjut(){
        return this.viestiketjut;
    }
}
