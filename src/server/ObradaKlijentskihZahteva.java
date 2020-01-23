/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import domen.ClanskaKarta;
import domen.Delo;
import domen.Izlozba;
import domen.Postavka;
import domen.Ulaznica;
import domen.Vodic;
import domen.Zaposleni;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    List<ObradaKlijentskihZahteva> listaKlijenata;
    Socket s;

    public ObradaKlijentskihZahteva(List<ObradaKlijentskihZahteva> listaKlijenata, Socket s) {
        this.listaKlijenata = listaKlijenata;
        
        this.s = s;
    }

  
    
    
    @Override
    public void run() {
        System.out.println("Klijentska nit je pokrenuta");
        while(true){
            try {
                KlijentskiZahtev kz = primiZahtev();
                ServerskiOdgovor so = new ServerskiOdgovor();
                switch(kz.getOperacija()){
                    case Operacije.PRIJAVA_ZAPOSLENOG:
                        Zaposleni z = (Zaposleni) kz.getParametar();
                        so = Kontroler.getInstanca().prijaviZaposlenog(z);
                        
                        Zaposleni zap = (Zaposleni) so.getParametar();
                        if(zap!=null){
                        List<Zaposleni> listaZaposlenih = Kontroler.getInstanca().getListaZaposlenih();
                        boolean daLiPostoji = Kontroler.getInstanca().proveriPrijavljenostZaposlenog(zap);
                        if(daLiPostoji == false){
                            listaZaposlenih.add(zap);
                            Kontroler.getInstanca().getSf().vratiTabeluZaposlenih().dodajZaposlenog(zap);
                        }
                        if(daLiPostoji == true){
                            so.setParametar(null);
                            so.setPoruka("Taj zaposleni je vec prijavljen na sistem.");
                            so.setUspesno(false);
                        }
                       }else{
                            so.setPoruka("Zaposleni sa tim parametrima ne postoji.");
                        }
                        break;
                    case Operacije.VRATI_LISTU_POSEBNIH_KATEGORIJA:
                        so.setParametar(Kontroler.getInstanca().getListaKategorija());
                        break;
                    case Operacije.KREIRAJ_CLANSKU_KARTU:
                        ClanskaKarta ck = (ClanskaKarta) kz.getParametar();
                        so = Kontroler.getInstanca().kreirajClanskuKartu(ck);                        
                        break;
                    case Operacije.VRATI_LISTU_DELA:
                        Delo d = (Delo) kz.getParametar();
                        so = Kontroler.getInstanca().vratiListuDela(d);
                        break;
                    case Operacije.VRATI_IZLOZBA_ID:
                        Izlozba i  = (Izlozba) kz.getParametar();
                        so = Kontroler.getInstanca().vratiIzlozbaID(i);
                        break;
                    case Operacije.KREIRAJ_IZLOZBU:
                        Izlozba izlozbaPostavki = (Izlozba) kz.getParametar();
                         so = Kontroler.getInstanca().kreirajIzlozbu(izlozbaPostavki);
                       
                       /* ArrayList<Postavka> lista = (ArrayList<Postavka>) kz.getParametar();
                        Izlozba izlozbaPostavki = lista.get(0).getIzlozba();
                        so = Kontroler.getInstanca().kreirajIzlozbu(izlozbaPostavki);
                        if (so.isUspesno()) {
                            so = Kontroler.getInstanca().kreirajPostavku(lista);
                            if (so.isUspesno()) {
                                so.setPoruka("ucitano");
                            }*/
                        

                        break;
                    case Operacije.VRATI_LISTU_IZLOZBI:
                        Izlozba izlozba = (Izlozba) kz.getParametar();
                        so = Kontroler.getInstanca().vratiListuIzlozbi(izlozba);
                        break; 
                    case Operacije.KREIRAJ_ULAZNICU:
                        Ulaznica ulaznica = (Ulaznica) kz.getParametar();
                        so = Kontroler.getInstanca().kreirajUlaznicu(ulaznica);
                        break;
                    case Operacije.KREIRAJ_VODICA:
                          Vodic vodic = (Vodic) kz.getParametar();
                          so = Kontroler.getInstanca().kreirajVodica(vodic);
                          break;
                    case Operacije.VRATI_LISTU_VODICA:
                        Vodic v = (Vodic) kz.getParametar();
                        so = Kontroler.getInstanca().vratiListuVodica(v);
                        break; 
                    case Operacije.NADJI_VODICE:
                        Vodic vod = (Vodic) kz.getParametar();
                        so = Kontroler.getInstanca().nadjiVodice(vod);
                        break; 
                     case Operacije.VRATI_LISTU_CLANSKIH_KARATA:
                        ClanskaKarta clanska = (ClanskaKarta) kz.getParametar();
                        so = Kontroler.getInstanca().vratiListuClanskihKarata(clanska);
                        break;   
                    case Operacije.PRETRAZI_CLANSKE_KARTE:
                        ClanskaKarta cl = (ClanskaKarta) kz.getParametar();
                        so = Kontroler.getInstanca().pretraziClanskeKarte(cl);
                        break;  
                    case Operacije.VRATI_CLANSKU_KARTU:
                        ClanskaKarta cl1 = (ClanskaKarta) kz.getParametar();
                        so = Kontroler.getInstanca().vratiClanskuKartu(cl1);
                        break;
                    case Operacije.ZAPAMTI_CLANSKU_KARTU:
                        ClanskaKarta cl2 = (ClanskaKarta) kz.getParametar();
                        so = Kontroler.getInstanca().zapamtiClanskuKartu(cl2);
                        break;
                    case Operacije.NADJI_IZLOZBE:
                        Izlozba iz = (Izlozba) kz.getParametar();
                        so = Kontroler.getInstanca().nadjiIzlozbe(iz);
                        break;
                   case Operacije.UCITAJ_IZLOZBU:
                        Izlozba izl = (Izlozba) kz.getParametar();
                        so = Kontroler.getInstanca().ucitajIzlozbu(izl);
                        break;
                    case Operacije.OBRISI_IZLOZBU:
                        Izlozba iz2 = (Izlozba) kz.getParametar();
                        so = Kontroler.getInstanca().obrisiIzlozbu(iz2);
                        break; 
                    case Operacije.UCITAJ_DELA:
                        Izlozba iz3 = (Izlozba) kz.getParametar();
                        so = Kontroler.getInstanca().ucitajDela(iz3);
                        break;      
                }
                posaljiOdgovor(so);
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
