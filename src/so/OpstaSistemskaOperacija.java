/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public abstract class OpstaSistemskaOperacija {

    public DBBroker db;

    public OpstaSistemskaOperacija() {
        db = new DBBroker();
    }

    public ServerskiOdgovor izvrsiTransakciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {

            so = izvrsiKonkretnuOperaciju(odo);
            potvrdi();
        } catch (Exception ex) {
            ponisti();
            throw new Exception("Greska prilikom izvrsenja SO" + ex.getMessage());
        } finally {
            db.zatvoriKonekciju();
        }

        return so;
    }
 
    
    protected abstract ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception;

    private void potvrdi() {
        db.commit();
    }

    private void ponisti() {
        db.rollback();;
    }

}
