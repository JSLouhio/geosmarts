package Dao;

import geosmarts.geosmarts.Database;;
import geosmarts.geosmarts.Tietokantataulut.Viesti;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ViestiDao implements Dao<Viesti, Integer> {

    private Database db;

    public ViestiDao(Database d) {
        this.db = d;
    }

    @Override
    public Viesti findOne(Integer key) throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Viesti WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        int id = rs.getInt("Id");
        int kayttajaId = rs.getInt("KayttajaId");
        int viestiketjuId = rs.getInt("ViestiketjuId");
        String sisalto = rs.getString("Sisalto");
        Date aika = rs.getDate("aika");

        Viesti v = new Viesti(id, kayttajaId, viestiketjuId, sisalto, aika);

        rs.close();

        stmt.close();

        this.db.disconnect();

        this.db.lisaaViesti(v);

        return v;
    }

    @Override
    public List<Viesti> findAll() throws SQLException {
        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT * FROM Viesti");

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        while (rs.next()) {

            int id = rs.getInt("Id");
            int kayttajaId = rs.getInt("KayttajaId");
            int viestiketjuId = rs.getInt("ViestiketjuId");
            String sisalto = rs.getString("Sisalto");
            Date aika = rs.getDate("aika");

            Viesti v = new Viesti(id, kayttajaId, viestiketjuId, sisalto, aika);
            viestit.add(v);
            this.db.lisaaViesti(v);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();

        return viestit;
    }

    @Override
    public List<Viesti> AllIn(Collection<Integer> keys) throws SQLException {
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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Viesti WHERE " + jotain + " IN (" + muuttujat + ")");
        int laskuri = 1;
        for (int key : keys) {
            stmt.setObject(laskuri, key);
            laskuri++;
        }

        ResultSet rs = stmt.executeQuery();
        List<Viesti> viestit = new ArrayList<>();

        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        while (rs.next()) {
            int id = rs.getInt("Id");
            int kayttajaId = rs.getInt("KayttajaId");
            int viestiketjuId = rs.getInt("ViestiketjuId");
            String sisalto = rs.getString("Sisalto");
            Date aika = rs.getDate("aika");

            Viesti v = new Viesti(id, kayttajaId, viestiketjuId, sisalto, aika);
            viestit.add(v);
            this.db.lisaaViesti(v);
        }
        rs.close();
        stmt.close();
        this.db.disconnect();
        return viestit;
    }

    @Override
    public void delete(Integer key) throws SQLException {

        ArrayList<Viesti> viestit = this.db.getViestit();
        int poistettava = 0;
        boolean loytyy = false;

        for (Viesti v : viestit) {
            if (v.getId() == (key)) {
                loytyy = true;
            }
            poistettava++;
        }
        this.db.getViestit().remove(poistettava);

    }

}
