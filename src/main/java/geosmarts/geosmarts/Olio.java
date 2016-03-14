package geosmarts.geosmarts;

public class Olio {

    private String nimi;
    private String viestit;
    private String pvm;
    private String aihe;
    private String nimim;
    private String sisalto;

    public Olio() {

    }

    public void setNimi(String n) {
        this.nimi = n;
    }

    public void setViestit(String n) {
        this.viestit = n;
    }

    public void setAihe(String a) {
        this.aihe = a;
    }

    public void setPvm(String n) {
        this.pvm = n;
    }

    public void setNimim(String n) {
        this.nimim = n;
    }

    public void setSisalto(String s) {
        this.sisalto = s;
    }

    public String getNimi() {
        return this.nimi;
    }

    public String getNimim() {
        return this.nimim;
    }

    public String getSisalto() {
        return this.sisalto;
    }

    public String getViestit() {
        return this.viestit;
    }

    public String getPvm() {
        return this.pvm;
    }

    public String getAihe() {
        return this.aihe;
    }
}
