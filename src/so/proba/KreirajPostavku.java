/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.proba;

import db.DBBroker;
import domen.Izlozba;
import domen.OpstiDomenskiObjekat;
import domen.Postavka;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class KreirajPostavku {

    DBBroker db;

    public KreirajPostavku() {
        db = new DBBroker();
    }

    public ServerskiOdgovor izvrsiTransakciju(ArrayList<Postavka> lista) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        db.ucitajDrajver();
        db.otvoriKonekciju();
        try {
            for (Postavka postavka : lista) {
                db.kreirajNoviObjekat(postavka);
            }
            db.commit();
            so.setUspesno(true);
        } catch (Exception e) {
            db.rollback();
            so.setUspesno(false);
            throw new Exception("Postavka nije uspesno sacuvana.");
        }

        return so;

    }

}
