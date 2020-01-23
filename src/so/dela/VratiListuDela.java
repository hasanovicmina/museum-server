/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dela;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class VratiListuDela extends OpstaSistemskaOperacija{

    public VratiListuDela() {
        super();
    }

    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
       try{ 
        List<OpstiDomenskiObjekat> listaDela = odo.ucitajRS(db.vratiObjekatBezWhere(odo));
        String poruka = "Uspešno pronađena lista dela.";
        System.out.println(poruka);
        so.setParametar(listaDela);
        so.setUspesno(true);
        so.setPoruka(poruka);
       }catch(Exception e){
           throw new Exception(" Nije pronađena lista dela. ");
       } 
        return so;
    }
    
}
