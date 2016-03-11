package Dao;

import geosmarts.geosmarts.Database;
import geosmarts.geosmarts.Tietokantataulut.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ViestiketjuDao implements Dao<Viestiketju, Integer> {

    private Database db;

    public ViestiketjuDao(Database d) {
        this.db = d;
    }

    @Override
    public Viestiketju findOne(Integer key) throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Viestiketju WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("Id");
        String aihe = rs.getString("Aihe");
        Date aika = rs.getDate("aika");

        Viestiketju v = new Viestiketju(id, aihe, aika);

        rs.close();

        stmt.close();

        this.db.disconnect();

        this.db.lisaaViestiketju(v);

        return v;
    }

    @Override
    public List<Viestiketju> findAll() throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Viestiketju");

        ResultSet rs = stmt.executeQuery();
        List<Viestiketju> viestiketjut = new ArrayList<>();

        while (rs.next()) {

            int id = rs.getInt("Id");
            String aihe = rs.getString("Aihe");
            Date aika = rs.getDate("aika");

            Viestiketju v = new Viestiketju(id, aihe, aika);
            viestiketjut.add(v);
            this.db.lisaaViestiketju(v);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();

        return viestiketjut;
    }

    @Override
    public List<Viestiketju> AllIn(Collection<Integer> keys) throws SQLException {
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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viestiketju WHERE " + jotain + " IN (" + muuttujat + ")");
        int laskuri = 1;
        for (int key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Viestiketju> viestiketjut = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("Id");
            String aihe = rs.getString("Aihe");
            Date aika = rs.getDate("aika");

            Viestiketju v = new Viestiketju(id, aihe, aika);
            viestiketjut.add(v);
            this.db.lisaaViestiketju(v);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();
        return viestiketjut;
    }

    @Override
    public void delete(Integer key) throws SQLException {

        ArrayList<Viestiketju> viestiketjut = this.db.getViestiketjut();
        int poistettava = 0;
        boolean loytyy = false;

        for (Viestiketju v : viestiketjut) {
            if (v.getId() == (key)) {
                loytyy = true;
            }
            poistettava++;
        }
        this.db.getViestiketjut().remove(poistettava);

    }

    @Override
    public List<Viestiketju> AlueetYhtAika() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Viestiketju vk) throws SQLException {
        String lisattava = vk.getAihe();

        String sql = "INSERT INTO Viestiketju "
                + "(aihe) VALUES ('"
                + (lisattava)
                + "');";
        db.update(sql);
    }

}
