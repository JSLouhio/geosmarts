package geosmarts.geosmarts;

import Dao.AlueDao;
import Dao.StringDao;
import Dao.ViestiDao;
import geosmarts.geosmarts.Tietokantataulut.Alue;
import geosmarts.geosmarts.Tietokantataulut.Viesti;
import java.sql.*;
import java.sql.Statement;
import java.util.*;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) throws Exception {

        alustaThyme();

        //////
    }

    public static void alustaThyme() throws SQLException {
        Database db = new Database();

        StringDao sd = new StringDao(db);

        AlueDao ad = new AlueDao(db);

        List<String> aaa = sd.AlueetYhtAika();

        String alpha = aaa.get(0);
        String bravo = aaa.get(1);
        String carlos = aaa.get(2);

        String[] alueet = alpha.split(",");
        List<String> ac = Arrays.asList(alueet);

        String[] viestit = bravo.split(",");
        List<String> vc = Arrays.asList(viestit);

        String[] paivays = carlos.split(",");
        List<String> pc = Arrays.asList(paivays);

//        System.out.println("alueet: " + aluecontainer);
//        System.out.println("viestit: " + viesticontainer);
//        System.out.println("päivöt: " + paivayscontainer);
        String a = ac.get(0);
        String b = vc.get(0);
        String c = pc.get(0);
        String d = ac.get(1);
        String e = vc.get(1);
        String f = pc.get(1);
        String g = ac.get(2);
        String h = vc.get(2);
        String i = pc.get(2);

         // kysy ja lisaa alueen nimi
        post("/", (req, res) -> {
            String nimi = req.queryParams("keskustelualue");
            Alue uusi_alue = new Alue(nimi);
            ad.create(uusi_alue);

            return "Lisätty alue: " + nimi;
//            return false;
        });

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("ida", a);                  //  1 alueet
            map.put("idb", b);                  //  1 viestit
            map.put("idc", c);                  //  1 timestamp

            map.put("idd", d);                  //  2 alueet
            map.put("ide", e);                  //  2 viestit
            map.put("idf", f);                  //  2 timestamp

            map.put("idg", g);                  //  3 alueet
            map.put("idh", h);                  //  3 viestit
            map.put("idi", i);                  //  3 timestamp

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
    }

}
