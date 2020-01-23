/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.izlozba;

import domen.Izlozba;
import domen.OpstiDomenskiObjekat;
import domen.Postavka;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class KreirajIzlozbu extends OpstaSistemskaOperacija{

    public KreirajIzlozbu() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();

        Izlozba izlozba = (Izlozba) odo;
        try {
            int id = db.vratiObjekatID(odo);
            izlozba.setIzlozbaID(id);
            db.kreirajNoviObjekat(izlozba);
            ArrayList<Postavka> lista = izlozba.getListaPostavki();
             for (Postavka postavka : lista) {
                db.kreirajNoviObjekat(postavka);
            }
            so.setParametar(izlozba);
            so.setUspesno(true);
        } catch (Exception e) {
            so.setUspesno(false);
            throw new Exception("Sistem ne može da kreira izložbu.");
        }
        return so;
    }
    
}
