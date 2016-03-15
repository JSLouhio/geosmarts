package geosmarts.geosmarts.Tietokantataulut;

import java.util.*;

public class Viesti {

    private int id;
    private int kayttajaId;
    private String nimi;
    private String aihe;
    private int viestiketjuId;
    private String sisalto;
    private Date aika;
    private String aihealueenNimi;
    
    public Viesti() {

    }

    public Viesti(int id, int kid, int vid, String s, Date d) {
        this.id = id;
        this.kayttajaId = kid;
        this.viestiketjuId = vid;
        this.sisalto = s;
        this.aika = d;
        
    }

    public Viesti(int id, int kid, int vid, String s, Date d, String n, String a) {
        this.id = id;
        this.kayttajaId = kid;
        this.viestiketjuId = vid;
        this.sisalto = s;
        this.aika = d;
        this.nimi=n;
        this.aihe=a;
    }
    
    public Viesti(int id, int kid, int vid, String s, Date d, String n, String a, String an) {
        this.id = id;
        this.kayttajaId = kid;
        this.viestiketjuId = vid;
        this.sisalto = s;
        this.aika = d;
        this.nimi=n;
        this.aihe=a;
        this.aihealueenNimi=an;
    }
    public void setId(int i) {
        this.id = i;
    }

    public String getNimi(){
        return this.nimi;
    }
    public void setKayttId(int i) {
        this.kayttajaId = i;
    }

    public void setViestiKetjId(int i) {
        this.viestiketjuId = i;
    }

    public void setSisalto(String sisalla) {
        this.sisalto = sisalla;
    }

    public void setAika() {
        this.aika = new Date();
    }

    public void setAika(Date d) {
        this.aika = d;
    }

    public String getAihe(){
        return this.aihe;
    }
    
    public int getId() {
        return this.id;
    }

    public int getKayttajIs() {
        return this.kayttajaId;
    }

    public int getViestiKetjId() {
        return this.viestiketjuId;
    }

    public String getSisalto() {
        return this.sisalto;
    }

    public String getAihealueennimi(){
        return this.aihealueenNimi;
    }
    
    public Date getAika() {
        return this.aika;
    }

//    public String toString() {
//
//        int id = this.getId();
//        int kayttajaId = this.getKayttajIs();
//        int viestiketjuId = this.getViestiKetjId();
//        String sisalto = this.getSisalto();
//        Date aika = this.getAika();
//
//        String palaute = "";
//
//        palaute = palaute + "viestin ID: "+id + "\n" + "Käyttäjän ID: "+ kayttajaId + "\n" +"Viestiketjun ID: "+ viestiketjuId + "\n" + "Sisältö: "+ sisalto + "\n" +"Aika: "+ aika+"\n"+"__________________________________";
//        return palaute;
//
//    }

}
