/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Izlozba;
import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hasan
 */
public class DBBroker {
    Connection konekcija;
    
    public void ucitajDrajver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drajver je ucitaj.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Drajver nije pronadjen.");
        }
    }
    
    public void otvoriKonekciju(){
        String url = "jdbc:mysql://localhost:3306/narodni_muzej_baza";
        String username  = "root";
        String pass = "";
        try {
            konekcija = DriverManager.getConnection(url, username, pass);
            konekcija.setAutoCommit(false);
            System.out.println("Uspesno uspostavljena konekcija");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Konekcija nije uspostavljena.");
        }
    }
    
     public void zatvoriKonekciju() {
        try {
            konekcija.close();
            System.out.println("Uspesno zatvorena konekcije.");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Zatvaranje konekcije nije uspelo.");
        }
    }

    public void rollback() {
        try {
            konekcija.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Rollback nije uspeo.");
        }
    }

    public void commit() {
        try {
            konekcija.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Commit nije uspeo.");
        }
    }

    public ResultSet vratiObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM "+ odo.vratiNazivTabele() + " " + odo.vratiUslovWhere();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return rs;
    }
    
      public ResultSet vratiObjekatBezWhere(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM "+ odo.vratiNazivTabele();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return rs;
    }

    public void kreirajNoviObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + odo.vratiKoloneInsert()+" VALUES (" + odo.vratiZaInsert() + ")";
        System.out.println(upit);
        Statement ps = konekcija.createStatement();
        ps.executeUpdate(upit);
        
    }

    public int vratiObjekatID(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT `auto_increment` FROM INFORMATION_SCHEMA.TABLES WHERE table_name = 'izlozba'";
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);

        return odo.vratiSlobodanID(rs);
    }

    public void izmeniObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiZaUpdate()+ " where " + odo.vratiPK();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }

    public void obrisiObjekat(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.vratiNazivTabele() + " where " + odo.vratiPK();
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        s.executeUpdate(upit);
    }

    public ResultSet vratiJoin(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT d.deloID AS ID, d.Naziv AS Naziv, d.Autor AS Autor, d.Tip AS Tip FROM "+odo.vratiNazivTabele()+" i JOIN  postavka p ON i.IzlozbaID = p.IzlozbaID JOIN delo d ON p.DeloID = d.DeloID WHERE i."+odo.vratiPK();
        
        System.out.println(upit);
        Statement s = konekcija.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return rs;   
    }


}
