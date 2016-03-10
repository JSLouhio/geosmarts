package Dao;

import java.util.*;
import java.sql.*;

// eli näissä Daoi:ssa saattoi olla hieman epäselvyyksiä; Tämä Dao on super-luokka, jonka muut Daot perivät, eli ne toteuttavat kaikki tämän metodit.
// T viittaa haettavaan asiaan ja K hakuperusteeseen, ne voivat periaatteessa olla mitätahansa olioita.
// vrt. KayttajaDao jne..

public interface Dao<T, K> {

    //etsi yksi
    T findOne(K key) throws SQLException;

    //etsi kaikki (eli SELECT * FROM ...)
    List<T> findAll() throws SQLException;

    //etsi osajoukko
    List<T> AllIn(Collection<K> keys) throws SQLException;
    
    //poista
    void delete(K key) throws SQLException;
    
    List<T> AlueetYhtAika() throws SQLException;
    
    
}
