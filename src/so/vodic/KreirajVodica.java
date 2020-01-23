/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vodic;

import domen.OpstiDomenskiObjekat;
import domen.Vodic;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class KreirajVodica extends OpstaSistemskaOperacija{

    public KreirajVodica() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            Vodic vodic = (Vodic) odo;
            db.kreirajNoviObjekat(odo);

            so.setPoruka("Sistem je uspešno dodao vodiča.");
            so.setUspesno(true);
        } catch (Exception e) {
            so.setPoruka("Sistem nije dodao vodiča.");
            so.setUspesno(false);
            throw new Exception("Sistem ne moze da doda vodica.");

        }


        
        return so;
    }
    
}
