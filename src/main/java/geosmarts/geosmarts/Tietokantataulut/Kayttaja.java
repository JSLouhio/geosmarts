package geosmarts.geosmarts.Tietokantataulut;

import java.util.ArrayList;
import java.util.Date;

public class Kayttaja {

    private int id;
    private String nimi;
    private Date liittynyt;
    private boolean status;
    private ArrayList<Viesti> viestit;

    public Kayttaja() {
        this.viestit = new ArrayList();
    }

    public Kayttaja(int i,String n, Date d, Boolean b) {
        this.id=i;
        this.nimi=n;
        this.liittynyt=d;
        this.status=b;
        this.viestit = new ArrayList();
    }
    
    
    public void setId(int i) {
        this.id = i;
    }

    public void setNimi(String a) {
        this.nimi = a;
    }

    //metodi asettaa tämän hetken pvm:ksi:
    //Date -formaatti pitää varmaan vielä hioa sopivaksi (dd:mm:yyyy?)
    public void setPvm() {
        Date d = new Date();
        this.liittynyt = d;
    }

    //tämä puolestaan ennalta määritellyn ajan d
    public void setPvm(Date d) {
        this.liittynyt = d;
    }

    public void setStatus(Boolean b) {
        this.status = b;
    }

    public void lisaaViesti(Viesti v) {
        this.viestit.add(v);
    }

    public int getId() {
        return this.id;
    }

    public String getNimi() {
        return this.nimi;
    }

    public Date getLiittynyt() {
        return this.liittynyt;
    }

    public boolean getStatus() {
        return this.status;
    }

    public ArrayList<Viesti> getViestit() {
        return this.viestit;
    }

}
