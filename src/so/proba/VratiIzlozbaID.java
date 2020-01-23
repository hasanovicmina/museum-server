/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.proba;

import domen.Izlozba;
import domen.OpstiDomenskiObjekat;
import domen.Postavka;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class VratiIzlozbaID extends OpstaSistemskaOperacija{

    public VratiIzlozbaID() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Izlozba i = (Izlozba) odo;
        try {
            int id = db.vratiObjekatID(odo);
            i.setIzlozbaID(id);
            so.setUspesno(true);
            so.setParametar(i);

        } catch (Exception e) {
            so.setUspesno(false);
            so.setParametar(null);
            System.out.println("Neuspesno kreirana nova izlozba.");

        }

        return so;
    }
    
}
