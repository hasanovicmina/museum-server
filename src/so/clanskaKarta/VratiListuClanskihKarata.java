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
public class VratiListuClanskihKarata extends OpstaSistemskaOperacija{

    public VratiListuClanskihKarata() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
          ServerskiOdgovor so = new ServerskiOdgovor();
              try{ 
        List<OpstiDomenskiObjekat> listaKarata = odo.ucitajRS(db.vratiObjekatBezWhere(odo));
        String poruka = "Uspešno pronađena lista članskih karata.";
        System.out.println(poruka);
        so.setParametar(listaKarata);
        so.setUspesno(true);
        so.setPoruka(poruka);
       }catch(Exception e){
            so.setUspesno(false);
            so.setParametar(null);
            throw new Exception(" Nije pronađena lista clanskih karata. ");
        }
        return so;
    }
    
}
