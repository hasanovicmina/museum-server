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
public class VratiListuIzlozbi extends OpstaSistemskaOperacija{

    public VratiListuIzlozbi() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
              try{ 
        List<OpstiDomenskiObjekat> listaIzlozbi = odo.ucitajRS(db.vratiObjekatBezWhere(odo));
        String poruka = "Uspešno pronađena lista izložbi.";
        System.out.println(poruka);
        so.setParametar(listaIzlozbi);
        so.setUspesno(true);
        so.setPoruka(poruka);
       }catch(Exception e){
            so.setUspesno(false);
            so.setParametar(null);
            throw new Exception(" Nije pronađena lista izložbi. ");
        }
        return so;
    }
    
}
