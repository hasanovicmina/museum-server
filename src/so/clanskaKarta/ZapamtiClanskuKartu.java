/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clanskaKarta;

import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class ZapamtiClanskuKartu extends OpstaSistemskaOperacija{

    public ZapamtiClanskuKartu() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();

        try {

            db.izmeniObjekat(odo);

            so.setPoruka("Izmena nad članskom kartom je uspešna.");
            so.setUspesno(true);

        } catch (Exception e) {
            e.printStackTrace();
            so.setUspesno(false);
            throw new Exception("Neuspešna izmena nas članskom kartom.");
        }
        return so;
    }

}
