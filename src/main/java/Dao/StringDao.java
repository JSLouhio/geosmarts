package Dao;

import geosmarts.geosmarts.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringDao implements Dao<String, Integer> {

    private Database db;

    public StringDao(Database d) {
        this.db = d;
    }

    @Override
    public String findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> AllIn(Collection<Integer> keys) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> AlueetYhtAika() throws SQLException {

        this.db.connect();
        PreparedStatement stmt = this.db.getConnection().prepareStatement("SELECT DISTINCT alue.nimi,Count(viesti.id_viesti), MAX(viesti.aika) FROM alue\n"
                + "INNER JOIN viestiketju ON alue.id_alue = viestiketju.alue_id\n"
                + "INNER JOIN viesti ON viestiketju.id_viestiketju = viesti.viestiketju_id\n"
                + "group by alue.nimi");

        ResultSet rs = stmt.executeQuery();
        List<String> alueetYhteensaJaAika = new ArrayList<>();
        
        

        while (rs.next()) {

            String nimi = rs.getString(1);
            String viesteja = String.valueOf(rs.getInt(2));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String aikataulu = dateFormat.format(rs.getTimestamp(3));

            String a = nimi + " " + viesteja + " " + aikataulu;
            alueetYhteensaJaAika.add(a);

        }
        rs.close();
        stmt.close();
        this.db.disconnect();

        return alueetYhteensaJaAika;

    }
}
