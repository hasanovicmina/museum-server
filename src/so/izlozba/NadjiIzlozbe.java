/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.izlozba;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class NadjiIzlozbe extends OpstaSistemskaOperacija{

    public NadjiIzlozbe() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
            ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            List<OpstiDomenskiObjekat> lista = odo.ucitajRS(db.vratiObjekat(odo));
                
            so.setPoruka("Sistem je pronasao izložbu po zadatom kriterijumu");
            so.setParametar(lista);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da pronadje izložbu po zadatom kriterijumu");
        }
        return so;
    }
    
}
