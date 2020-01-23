/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ulaznice;

import domen.OpstiDomenskiObjekat;
import domen.Ulaznica;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class KreirajUlaznicu extends OpstaSistemskaOperacija{

    public KreirajUlaznicu() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        
        Ulaznica ulaznica = (Ulaznica) odo;
        try {
            db.kreirajNoviObjekat(odo);
            so.setPoruka("Sistem je uspešno dodao ulaznicu.");
            so.setUspesno(true);
        } catch (Exception e) {
            so.setUspesno(false);
            so.setPoruka("Sistem nije uspeo da doda ulaznicu.");

        }


        
        
        return so;
    }
    
}
