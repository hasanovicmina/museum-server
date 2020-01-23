/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clanskaKarta;

import domen.ClanskaKarta;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class KreirajClanskuKartu extends OpstaSistemskaOperacija{

    public KreirajClanskuKartu() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            ClanskaKarta clanskaKarta = (ClanskaKarta) odo;
            db.kreirajNoviObjekat(odo);

            so.setPoruka("Sistem je uspešno dodao člansku kartu.");
            so.setUspesno(true);
        } catch (Exception e) {
            so.setPoruka("Neuspešno dodavanje članske karte.");
            so.setUspesno(false);
            throw new Exception("Sistem ne moze da doda clansku kartu.");
        }

        return so;
    }
    
}
