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

        Database db = new Database();

        StringDao sd = new StringDao(db);
        
        
        ArrayList<String> aaa = new ArrayList<String>(sd.AlueetYhtAika());
        String sana = "";
        
        
        for (String s : aaa) {
            sana = sana + s + "\n";
            
        }

        String hei = sana;
        get("/hei", (req, res) -> {
            return hei;
        });

        //////
    }
}
