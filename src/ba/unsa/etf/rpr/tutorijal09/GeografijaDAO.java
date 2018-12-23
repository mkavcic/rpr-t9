package ba.unsa.etf.rpr.tutorijal09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static ba.unsa.etf.rpr.tutorijal09.Main.createNewDatabase;
import static ba.unsa.etf.rpr.tutorijal09.Main.createNewTable;

public class GeografijaDAO {
    ArrayList<Grad> gradovi=new ArrayList<>();
    ArrayList<Drzava> drzave=new ArrayList<>();
    private static GeografijaDAO instanca=null;
    private static Connection conn;
    public String url = "jdbc:sqlite/baza.db";
    private static PreparedStatement stmt;


    public static GeografijaDAO getInstance() {
        if(instanca==null) initialize();
        return instanca;
    }

    private  static void initialize() {
        if(instanca==null){
            createNewDatabase("baza");
            createNewTable();
            insertToDatabase();
        }
        instanca = new GeografijaDAO();
    }

    public static  void insertToDatabase(){
        connect();
        try {
            stmt = conn.prepareStatement("INSERT INTO drzava(id,naziv,glavni_grad) VALUES(1,'Velika Britanija',1)");
            stmt.execute();
            stmt = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(2,'Francuska',2)");
            stmt.execute();
            stmt = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(3,'Austrija',3)");
            stmt.execute();
            stmt = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(4,'Velika Britanija',4)");
            stmt.execute();
            stmt = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(5,'Austrija',5)");
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void connect(){  // source : https://www.codejava.net/java-se/jdbc/connect-to-a-database-with-jdbc
        try{
            String url = "jdbc:sqlite:baza.db";
            conn = DriverManager.getConnection(url);
            if (conn!=null)
                System.out.println("Spojeno.");
        }
        catch (SQLException ex) {
            System.err.println("Spajanje nije uspjelo."+ex.getMessage());
        }
    }

    Grad GlavniGrad(String drzava){
        Grad g=new Grad();
        return g;
    }

    public void obrisiDrzavu(String drzava){
        for(var x: drzave){
            if(x.getNaziv().equals(drzava)) drzave.remove(x);
        }
    }

    ArrayList<Grad> gradovi(){
        ArrayList<Grad> a=new ArrayList<>();
        return a;
    }

    void dodajGrad(Grad grad){
        gradovi.add(grad);
    }

    void dodajDrzavu(Drzava drzava){
        drzave.add(drzava);
    }

    void izmijeniGrad(Grad grad){
        try{
            stmt=conn.prepareStatement("UPDATE main.drzava SET main.dzava.naziv=?, main.drzava.glavniGrad=?, main.drzava.brojStanovnika=?");
            stmt.setInt(1, grad.getId());
            stmt.setString(2, grad.getNaziv());
            stmt.setInt(3, grad.getBrojStanovnika());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    Drzava nadjiDrzavu(){
        Drzava d=new Drzava();
        for(var x: drzave){
            if(x.getNaziv().equals(x)) d=x;
        }
        return d;
    }

    public Grad glavniGrad(String drzava){
        for(var x: drzave){
            if(x.getNaziv().equals(drzava)) return x.getGlavniGrad();
        }
        return null;
    }


}
