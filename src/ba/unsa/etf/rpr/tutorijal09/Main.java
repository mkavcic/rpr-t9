package ba.unsa.etf.rpr.tutorijal09;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void createNewTable(){  // source : http://www.sqlitetutorial.net/sqlite-java/create-table/
        String url="jdbc:sqlite:C://sqlite/db/baza.db";
        String grad="CREATE TABLEIF NOT EXISTS GRAD (\n"+" id int PRIMARY KEY, \n"+" naziv text NOT NULL,\n"+" CONSTRAINT grad_drzava_glavni_grad_fk FOREIGN KEY (id) REFERENCES drzava (glavni_grad)\n "+");";
        String drzava= "CREATE TABLE IF NOT EXISTS drzava (\n" + "	id int PRIMARY KEY,\n" + "	naziv text NOT NULL,\n" + "	CONSTRAINT drzava_grad_broj_stanovnika_fk FOREIGN KEY (id) REFERENCES grad (broj_stanovnika)\n" + ");";
        try(Connection conn= DriverManager.getConnection(url);
        Statement stmt=conn.createStatement();
        Statement stmt1=conn.createStatement()){
            stmt.execute(grad);
            stmt1.execute(drzava);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void createNewDatabase(String fileName){  //source : http://www.sqlitetutorial.net/sqlite-java/create-database/
        String url="jdbc:sqlite:C:/sqlite/db/"+fileName;
        try(Connection conn= DriverManager.getConnection(url)){
            if(conn!=null){
                DatabaseMetaData meta=conn.getMetaData();
                System.out.println("Drajver je "+ meta.getDriverName());
                System.out.println("Baza je kreirana.");
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }
}
