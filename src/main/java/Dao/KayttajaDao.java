package Dao;

import geosmarts.geosmarts.Database;
import geosmarts.geosmarts.Tietokantataulut.Kayttaja;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//metodi etsii käyttäjiä nimien perusteella. Voisi toimia myös Id:llä, silloin muotoa Dao<Kayttaja,int>
public class KayttajaDao implements Dao<Kayttaja, String> {

    private Database db;

    public KayttajaDao(Database d) {
        this.db = d;
    }

    @Override
    public Kayttaja findOne(String key) throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Kayttaja WHERE nimi = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        //näissä vois olla parametreina myös taulujen numerot nimien tilalla.
        int id = rs.getInt("Id");
        String nimi = rs.getString("nimi");
        Date liittynyt = rs.getDate("Liittynyt");
        boolean status = rs.getBoolean("Status");
        // listaa viesteistä ei saa ainakaan suoraan rs.get -metodien kautta

        Kayttaja k = new Kayttaja(id, nimi, liittynyt, status);

        rs.close();

        stmt.close();

        this.db.disconnect();
        this.db.lisaaKayttaja(k);
        return k;
    }

    //metodi etsii kaikki käyttäjät
    @Override
    public List<Kayttaja> findAll() throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Kayttaja");

        ResultSet rs = stmt.executeQuery();
        List<Kayttaja> kayttajat = new ArrayList<>();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        while (rs.next()) {
            int id = rs.getInt("Id");
            String nimi = rs.getString("nimi");
            Date liittynyt = rs.getDate("Liittynyt");
            boolean status = rs.getBoolean("Status");

            Kayttaja k = new Kayttaja(id, nimi, liittynyt, status);
            kayttajat.add(k);
            this.db.lisaaKayttaja(k);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();

        return kayttajat;
    }

    //tämä metodi etsii kaikki Kayttajat jonkin setin perusteella
    @Override
    public List<Kayttaja> AllIn(Collection<String> keys) throws SQLException {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder muuttujat = new StringBuilder("?");
        for (int i = 1; i < keys.size(); i++) {
            muuttujat.append(", ?");
        }

        this.db.connect();
        Connection connection = this.db.getConnection();
        String jotain = "";     //toinen taulu, josta haetaan
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kayttaja WHERE " + jotain + " IN (" + muuttujat + ")");
        int laskuri = 1;
        for (String key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Kayttaja> kayttajat = new ArrayList<>();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        while (rs.next()) {
            int id = rs.getInt("Id");
            String nimi = rs.getString("nimi");
            Date liittynyt = rs.getDate("Liittynyt");
            boolean status = rs.getBoolean("Status");

            Kayttaja k = new Kayttaja(id, nimi, liittynyt, status);
            kayttajat.add(k);
            this.db.lisaaKayttaja(k);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();
        return kayttajat;
    }

    // poista käyttäjä
    @Override
    public void delete(String key) throws SQLException {

        ArrayList<Kayttaja> kayttajat = this.db.getKayttajat();
        int poistettava = 0;
        boolean loytyy = false;

        for (Kayttaja k : kayttajat) {
            if (k.getNimi().equals(key)) {
                loytyy = true;
            }
            poistettava++;
        }
        this.db.getKayttajat().remove(poistettava);
        
    }

}
