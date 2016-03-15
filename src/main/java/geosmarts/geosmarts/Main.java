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
<<<<<<< HEAD

=======
        
         // asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            port(Integer.valueOf(System.getenv("PORT")));
        }
           
>>>>>>> 197467c64d9ea3cca8da0c44cc989b1cb7ed220c
        Database db = new Database();

        StringDao sd = new StringDao(db);

        AlueDao ad = new AlueDao(db);
        ViestiketjuDao vd = new ViestiketjuDao(db);

        post("/", (req, res) -> {
            String nimi = req.queryParams("keskustelualue");
            Alue uusi_alue = new Alue(nimi);
            ad.create(uusi_alue);
<<<<<<< HEAD

            return "Lisätty alue: " + nimi;
=======
            
            return "LisÃ¤tty alue: " + nimi;
>>>>>>> 197467c64d9ea3cca8da0c44cc989b1cb7ed220c
        });

        post("/1", (req, res) -> {
            String aihe = req.queryParams("Viestiketju");
            Viestiketju uusi_vk = new Viestiketju(aihe);
            vd.create(uusi_vk);
<<<<<<< HEAD

            return "Lisätty alue: " + aihe;
=======
            
            return "LisÃ¤tty alue: " + aihe;
>>>>>>> 197467c64d9ea3cca8da0c44cc989b1cb7ed220c
        });
//        System.out.println("/alue/:numero/:viestiketjuntunnus");

        tulosta();
//////
    }

    public static void tulosta() throws SQLException {
        Database db = new Database();
        AlueDao ad = new AlueDao(db);
        StringDao sd = new StringDao(db);
        ViestiketjuDao vd = new ViestiketjuDao(db);
        ViestiDao vud = new ViestiDao(db);

        ArrayList<Olio> Aluelista = spliz(sd.AlueetYhtAika());
        ArrayList<Olio> ketjulista = spliz(sd.viestiketjut());
//        ArrayList<Olio> viestilista = spliz(sd.viestit());

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("aa", Aluelista);

            return new ModelAndView(map, "index2");
        }, new ThymeleafTemplateEngine());

//        get("/1", (req, res) -> {
//            HashMap map = new HashMap<>();
//
//            map.put("bb", ketjulista);
//            map.put("aa", Aluelista);
//
//            return new ModelAndView(map, "Viestiketju");
//        }, new ThymeleafTemplateEngine());

//           get("/2", (req, res) -> {
//            HashMap map = new HashMap<>();
//            
//            map.put("bb", ketjulista);
//            map.put("aa", Aluelista);
////            map.put("cc", viestilista);
//            
//            return new ModelAndView(map, "Viestit");
//        }, new ThymeleafTemplateEngine());
//           
        get("/keskustelu/:viestiketjuntunnus", (req, res) -> {
            HashMap map = new HashMap<>();
            int viestiketjunTunnus = Integer.parseInt(req.params(":viestiketjuntunnus"));

            map.put("bb", ketjulista);
            map.put("aa", Aluelista);;

            Alue alue = ad.findOne(viestiketjunTunnus);
            List<Viesti> viestit = vud.BallIn(viestiketjunTunnus);
            Viesti v = vud.findOne(viestiketjunTunnus);

            map.put("bb", ketjulista);
            map.put("alue", alue);
            map.put("viestit", viestit);
            map.put("yksi", v);
            
//            System.out.println("VVVVVV: "+v.getAihealueennimi());
            
            System.out.println(ketjulista);
            System.out.println(alue);
            System.out.println(viestit);

            return new ModelAndView(map, "viestit2");
        }, new ThymeleafTemplateEngine());

        get("/alue/:numero", (req, res) -> {
            HashMap map = new HashMap<>();
            int viestiketjunTunnus = Integer.parseInt(req.params(":numero"));

            Alue alue = ad.findOne(viestiketjunTunnus);
            List<Viestiketju> vk = vd.BallIn(viestiketjunTunnus);
//               System.out.println("aaaAAaaaaaaaaaaaaaaaaaaaa: "+vd.BallIn(viestiketjunTunnus));
            map.put("bb", ketjulista);
            map.put("alue", alue);
            map.put("viestiketju", vk);

//            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX: " + vk.get(0).getNumero());
            
            return new ModelAndView(map, "Viestiketju");
        }, new ThymeleafTemplateEngine());

    }

    public static ArrayList<Olio> spliz(List<String> aaaaa) throws SQLException {
        List<String> aaa = aaaaa;

        String alpha = aaa.get(0);
        String bravo = aaa.get(1);
        String carlos = aaa.get(2);
        String delta = aaa.get(3);

        String[] alueet = alpha.split(",");
        List<String> ac = Arrays.asList(alueet);

        String[] viestit = bravo.split(",");
        List<String> vc = Arrays.asList(viestit);

        String[] paivays = carlos.split(",");
        List<String> pc = Arrays.asList(paivays);

        String[] idet = delta.split(",");
        List<String> ic = Arrays.asList(idet);

//        System.out.println("alueet: " + ac);
//        System.out.println("viestit: " + vc);
<<<<<<< HEAD
//        System.out.println("päivöt: " + pc);
=======
//        System.out.println("pÃ¤ivÃ¶t: " + pc);
        
        
>>>>>>> 197467c64d9ea3cca8da0c44cc989b1cb7ed220c
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
            o.setId(ic.get(i));
            i++;
            lista.add(o);
        }

        return lista;
    }

}
