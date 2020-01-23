/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.dela;

import domen.Delo;
import domen.Izlozba;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class UcitajDela extends OpstaSistemskaOperacija{

    public UcitajDela() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Izlozba i = (Izlozba) odo;
        
        List<OpstiDomenskiObjekat> listaDela = odo.ucitajJoinRs(db.vratiJoin(odo));
       
        so.setParametar(listaDela);
        so.setUspesno(true);
      
        return so;
    }
    
}
