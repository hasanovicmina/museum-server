/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clanskaKarta;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class VratiClanskuKartu extends OpstaSistemskaOperacija{

    public VratiClanskuKartu() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            List<OpstiDomenskiObjekat> lista = odo.ucitajRS(db.vratiObjekat(odo));

            so.setPoruka("Sistem je pronasao clansku kartu po zadatom kriterijumu");
            so.setParametar(lista.get(0));
            so.setUspesno(true);
        } catch (Exception e) {
            so.setUspesno(false);
            throw new Exception("Sistem ne moze da pronadje clansku kartu po zadatom kriterijumu");
        }
        return so;
    }
    
    
}
