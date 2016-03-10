package geosmarts.geosmarts;

import geosmarts.geosmarts.Tietokantataulut.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database<T> {

    private java.sql.Connection connection;
    private Statement stmt;
    private boolean debug;
    private ArrayList<Kayttaja> kayttajat;
    private ArrayList<Viesti> viestit;
    private ArrayList<Viestiketju> viestiketjut;
    private ArrayList<Alue> alueet;

    public Database() {

        this.kayttajat = new ArrayList();
        this.viestit = new ArrayList();
        this.viestiketjut = new ArrayList();
        this.alueet = new ArrayList();
    }

    public void connect() throws SQLException {
        String nimi = "geosmarts";    //<- tähän tietokannan nimi (eli varmaan jotain tyyliin "geosmarts")
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + nimi + ".db");
        this.stmt = this.connection.createStatement();
    }

    public void disconnect() throws SQLException {
        this.stmt.close();
        this.connection.close();

    }

    public List<T> queryAndCollect(String query, Collector<T> col) throws SQLException {
        List<T> rows = new ArrayList<>();
        this.stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            if (debug) {
                System.out.println("---");
                System.out.println(query);
                debug(rs);
                System.out.println("---");
            }

            rows.add(col.collect(rs));
        }
        rs.close();
        stmt.close();
        return rows;
    }

    public void setDebugMode(boolean d) {
        debug = d;
    }

    private void debug(ResultSet rs) throws SQLException {
        int columns = rs.getMetaData().getColumnCount();
        for (int i = 0; i < columns; i++) {
            System.out.print(
                    rs.getObject(i + 1) + ":"
                    + rs.getMetaData().getColumnName(i + 1) + "  ");
        }

        System.out.println();
    }

    public int update(String updateQuery) throws SQLException {
        connect();
        this.stmt = connection.createStatement();
        int changes = stmt.executeUpdate(updateQuery);

        if (this.debug) {
            System.out.println("---");
            System.out.println(updateQuery);
            System.out.println("Changed rows: " + changes);
            System.out.println("---");
        } else {

        }

        this.stmt.close();
        disconnect();

        return changes;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void lisaaKayttaja(Kayttaja k) {
        this.kayttajat.add(k);
    }

    public ArrayList<Kayttaja> getKayttajat() {
        return this.kayttajat;
    }

    public void lisaaAlue(Alue a) {
        this.alueet.add(a);
    }

    public ArrayList<Alue> getAlueet() {
        return this.alueet;
    }

    public void lisaaViestiketju(Viestiketju v) {
        this.viestiketjut.add(v);
    }

    public ArrayList<Viestiketju> getViestiketjut() {
        return this.viestiketjut;
    }

    public void lisaaViesti(Viesti v) {
        this.viestit.add(v);
    }

    public ArrayList<Viesti> getViestit() {
        return this.viestit;
    }
}
