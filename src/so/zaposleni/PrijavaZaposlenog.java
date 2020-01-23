/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.zaposleni;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class PrijavaZaposlenog extends OpstaSistemskaOperacija{

    public PrijavaZaposlenog() {
        super();
    }

    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
       try{
        List<OpstiDomenskiObjekat> listaZaposlenih =  odo.ucitajRS(db.vratiObjekat(odo));
        if(listaZaposlenih.isEmpty()){
            throw  new Exception("U sistemu ne postoji zaposleni sa datim parametrima");
        }
        so.setParametar(listaZaposlenih.get(0));
        so.setPoruka("Zaposleni je pronadjen.");
        so.setUspesno(true);
       
       }catch(Exception e){
        so.setUspesno(false);
      }
       return so;
     }

    
    
    
}
