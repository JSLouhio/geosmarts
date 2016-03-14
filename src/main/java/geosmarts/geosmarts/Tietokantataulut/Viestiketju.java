package geosmarts.geosmarts.Tietokantataulut;

import java.util.*;

public class Viestiketju {

    private int id;
    private int alueID;
    private String aihe;
    private Date pvm;  //aloituspvm vai viimeisin viesti??
    private ArrayList<Viesti> viestit;

    public Viestiketju(int id, String a, Date d) {
        this.id = id;
        this.aihe = a;
        this.pvm = d;
        this.viestit = new ArrayList();
    }

    public Viestiketju(String aihe) {
        this.aihe = aihe;
        this.viestit = new ArrayList();
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setAihe(String a) {
        this.aihe = a;
    }

    //metodi asettaa tämän hetken pvm:ksi:
    //Date -formaatti pitää varmaan vielä hioa sopivaksi (dd:mm:yyyy?)
    public void setPvm() {
        Date d = new Date();
        this.pvm = d;
    }

    //tämä puolestaan ennalta määritellyn ajan d
    public void setPvm(Date d) {
        this.pvm = d;
    }

    public void lisaaViesti(Viesti v) {
        this.viestit.add(v);
    }

    public int getId() {
        return this.id;
    }

    public String getAihe() {
        return this.aihe;
    }

    public Date getPvm() {
        return this.pvm;
    }

    public ArrayList<Viesti> getViestit() {
        return this.viestit;
    }
}
