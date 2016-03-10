package Dao;

import geosmarts.geosmarts.Database;
import geosmarts.geosmarts.Tietokantataulut.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlueDao implements Dao<Alue, Integer> {

    private Database db;

    public AlueDao(Database d) {
        this.db = d;
    }

    @Override
    public Alue findOne(Integer key) throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Alue WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("Id");
        String nimi = rs.getString("Nimi");

        Alue a = new Alue(id, nimi);

        rs.close();

        stmt.close();

        this.db.disconnect();

        this.db.lisaaAlue(a);

        return a;
    }

    @Override
    public List<Alue> findAll() throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Alue");

        ResultSet rs = stmt.executeQuery();
        List<Alue> alueet = new ArrayList<>();


        while (rs.next()) {

            int id = rs.getInt(1);
            String nimi = rs.getString(2);

            Alue a = new Alue(id, nimi);
            alueet.add(a);
            this.db.lisaaAlue(a);
        }
        
        rs.close();
        stmt.close();
        this.db.disconnect();

        return alueet;
    }

    @Override
    public List<Alue> AllIn(Collection<Integer> keys) throws SQLException {
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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Alue WHERE " + jotain + " IN (" + muuttujat + ")");
        int laskuri = 1;
        for (int key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Alue> alueet = new ArrayList<>();


        while (rs.next()) {
            int id = rs.getInt("Id");
            String nimi = rs.getString("Nimi");

            Alue a = new Alue(id, nimi);
            alueet.add(a);
            this.db.lisaaAlue(a);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();
        return alueet;
    }

    @Override
    public void delete(Integer key) throws SQLException {

        ArrayList<Alue> alueet = this.db.getAlueet();
        int poistettava = 0;
        boolean loytyy = false;

        for (Alue v : alueet) {
            if (v.getId() == (key)) {
                loytyy = true;
            }
            poistettava++;
        }
        this.db.getAlueet().remove(poistettava);

    }

    @Override
    public List<Alue> AlueetYhtAika() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}

