/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.izlozba;

import domen.Izlozba;
import domen.OpstiDomenskiObjekat;
import domen.Postavka;
import domen.Ulaznica;
import domen.Vodic;
import java.util.List;
import so.OpstaSistemskaOperacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class ObrisiIzlozbu extends OpstaSistemskaOperacija{

    public ObrisiIzlozbu() {
        super();
    }

    
    
    @Override
    protected ServerskiOdgovor izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat odo) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        
        try {
            
           
            //Prolazi se kroz listu vodica, ulaznica i  postavki, ako je id testa isti kao onog kog brisemo, ZABRANJUJE SE BRISANJE IZLOZBE!
            // to zboog restrict uslova
            Izlozba izlozba = (Izlozba) odo;
            Vodic vodic = new Vodic(-1, "", null, izlozba.getIzlozbaID());
            List<OpstiDomenskiObjekat> listaVodica = vodic.ucitajRS(db.vratiObjekat(vodic));
            
            Postavka postavka = new Postavka(izlozba, null, "", 0, 0);
            List<OpstiDomenskiObjekat> listaPostavki = postavka.ucitajRS(db.vratiObjekat(postavka));

            Ulaznica ulaznica = new Ulaznica(-1, null, 0.0, null, izlozba);
            List<OpstiDomenskiObjekat> listaUlaznica = ulaznica.ucitajRS(db.vratiObjekat(ulaznica));


            if(listaVodica.size() != 0 || listaUlaznica.size() != 0 || listaPostavki.size() != 0){
                so.setUspesno(false);
                so.setPoruka("Brisanje izložbe nije uspelo!");
            }else{
                db.obrisiObjekat(odo);
                so.setPoruka("Sistem je uspesno obrisao test sa datim parametrima");
                so.setUspesno(true);
            
            }
        
        } catch (Exception e) {
            e.printStackTrace();
            so.setUspesno(false);
            throw new Exception("Test je neuspesno obrisan");
        }
        return so;
    }
    
}
