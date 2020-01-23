/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vodic;

import domen.OpstiDomenskiObjekat;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class VratiListuVodica extends OpstaSistemskaOperacija{

    public VratiListuVodica() {
        super();
     }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
              try{ 
        List<OpstiDomenskiObjekat> listaVodica = odo.ucitajRS(db.vratiObjekatBezWhere(odo));
        String poruka = "Uspešno pronađena lista vodiča.";
        System.out.println(poruka);
        so.setParametar(listaVodica);
        so.setUspesno(true);
        so.setPoruka(poruka);
       }catch(Exception e){
            so.setUspesno(false);
            so.setParametar(null);
            throw new Exception(" Nije pronađena lista vodiča. ");
        }
        return so;
    }
    
    
    
    
}
