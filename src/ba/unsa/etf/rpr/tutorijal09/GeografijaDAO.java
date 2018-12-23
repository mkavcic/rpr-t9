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
    private static final String INSERT_SQL1 = "INSERT INTO Remuneraciones(id, naziv, , glavni_grad) VALUES(?, ?, ?)";
    private static final String INSERT_SQL2 = "INSERT INTO Remuneraciones(id, naziv, , broj_stanovnika, drzava) VALUES(?, ?, ?,?)";
    private static Connection conn;  /* i ostalo što treba za bazu */
    public String url = "jdbc:sqlite/baza.db";
    private static PreparedStatement stmt1, stmt2;


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
            stmt1 = conn.prepareStatement("INSERT INTO drzava(id,naziv,glavni_grad) VALUES(1,'Velika Britanija',1)");
            stmt1.execute();
            stmt1 = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(2,'Francuska',2)");
            stmt1.execute();
            stmt1 = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(3,'Austrija',3)");
            stmt1.execute();
            stmt1 = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(4,'Velika Britanija',4)");
            stmt1.execute();
            stmt1 = conn.prepareStatement("INSERT INTO drzava(naziv,glavni_grad) VALUES(5,'Austrija',5)");
            stmt1.execute();
            stmt1.close();

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
            stmt1=conn.prepareStatement("UPDATE main.drzava SET main.dzava.naziv=?, main.drzava.glavniGrad=?, main.drzava.brojStanovnika=?");
            stmt1.setInt(1, grad.getId());
            stmt1.setString(2, grad.getNaziv());
            stmt1.setInt(3, grad.getBrojStanovnika());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    Drzava nadjiDrzavu(){
        Drzava d=new Drzava();
        return d;
    }

    String ispisiGradove(){
        String c="";
        return c;
    }

    public Grad glavniGrad(String drzava){
        for(var x: drzave){
            if(x.getNaziv().equals(drzava)) return x.getGlavniGrad();
        }
        return null;
    }


}
