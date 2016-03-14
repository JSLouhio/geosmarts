package geosmarts.geosmarts;

import Dao.AlueDao;
import Dao.StringDao;
import Dao.ViestiDao;
import Dao.ViestiketjuDao;
import geosmarts.geosmarts.Tietokantataulut.Alue;
import geosmarts.geosmarts.Tietokantataulut.Viesti;
import geosmarts.geosmarts.Tietokantataulut.Viestiketju;
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
        
        AlueDao ad = new AlueDao(db);
        ViestiketjuDao vd = new ViestiketjuDao(db);
        
        post("/", (req, res) -> {
            String nimi = req.queryParams("keskustelualue");
            Alue uusi_alue = new Alue(nimi);
            ad.create(uusi_alue);
            
            return "Lisätty alue: " + nimi;
        });
        
        post("/1", (req, res) -> {
            String aihe = req.queryParams("Viestiketju");
            Viestiketju uusi_vk = new Viestiketju(aihe);
            vd.create(uusi_vk);
            
            return "Lisätty alue: " + aihe;
        });
        
        tulosta();
//////
    }
    
    public static void tulosta() throws SQLException {
        Database db = new Database();
        AlueDao ad = new AlueDao(db);
        StringDao sd = new StringDao(db);
        
        ArrayList<Olio> Aluelista = spliz(sd.AlueetYhtAika());
        ArrayList<Olio> ketjulista = spliz(sd.viestiketjut());
        ArrayList<Olio> viestilista = spliz(sd.viestit());
        
        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aa", Aluelista);
            
            return new ModelAndView(map, "index2");
        }, new ThymeleafTemplateEngine());
        
        get("/1", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("bb", ketjulista);
            map.put("aa", Aluelista);
            
            return new ModelAndView(map, "Viestiketju");
        }, new ThymeleafTemplateEngine());
        
           get("/2", (req, res) -> {
            HashMap map = new HashMap<>();
            
            map.put("bb", ketjulista);
            map.put("aa", Aluelista);
            map.put("cc", viestilista);
            
            return new ModelAndView(map, "Viestit");
        }, new ThymeleafTemplateEngine());
        
    }
    
    public static ArrayList<Olio> spliz(List<String> aaaaa) throws SQLException {
        List<String> aaa = aaaaa;
        
        String alpha = aaa.get(0);
        String bravo = aaa.get(1);
        String carlos = aaa.get(2);
        
        String[] alueet = alpha.split(",");
        List<String> ac = Arrays.asList(alueet);
        
        String[] viestit = bravo.split(",");
        List<String> vc = Arrays.asList(viestit);
        
        String[] paivays = carlos.split(",");
        List<String> pc = Arrays.asList(paivays);

//        System.out.println("alueet: " + ac);
//        System.out.println("viestit: " + vc);
//        System.out.println("päivöt: " + pc);
        
        
        ArrayList<Olio> lista = new ArrayList();
        
        int i = 0;
        for (String r : ac) {
            Olio o = new Olio();
            o.setNimi(ac.get(i));
            o.setAihe(ac.get(i));
            o.setNimim(ac.get(i));
            o.setSisalto(vc.get(i));
            o.setViestit(vc.get(i));
            o.setPvm(pc.get(i));
            i++;
            lista.add(o);
        }
        
        return lista;
    }
    
}
