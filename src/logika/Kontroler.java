/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.DBBroker;
import domen.ClanskaKarta;
import domen.Delo;
import domen.Izlozba;
import domen.Postavka;
import domen.Ulaznica;
import domen.Vodic;
import domen.Zaposleni;
import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import so.clanskaKarta.KreirajClanskuKartu;
import so.clanskaKarta.PretraziClanskeKarte;
import so.clanskaKarta.VratiClanskuKartu;
import so.clanskaKarta.VratiListuClanskihKarata;
import so.clanskaKarta.ZapamtiClanskuKartu;
import so.dela.UcitajDela;
import so.dela.VratiListuDela;
import so.izlozba.KreirajIzlozbu;
import so.proba.VratiIzlozbaID;
import so.proba.KreirajPostavku;
import so.izlozba.NadjiIzlozbe;
import so.izlozba.ObrisiIzlozbu;
import so.izlozba.UcitajIzlozbu;
import so.izlozba.VratiListuIzlozbi;
import so.ulaznice.KreirajUlaznicu;
import so.vodic.KreirajVodica;
import so.vodic.NadjiVodica;
import so.vodic.VratiListuVodica;
import so.zaposleni.PrijavaZaposlenog;
import transfer.ServerskiOdgovor;

/**
 *
 * @author hasan
 */
public class Kontroler {
    private static Kontroler instanca;
    public  List<Zaposleni> listaZaposlenih;
    public  ArrayList<String> listaKategorija;

    DBBroker db;
    private ServerskaForma sf;
    
    private Kontroler() {
        db = new DBBroker();
        listaZaposlenih = new ArrayList<>();
        listaKategorija = napraviListuKategorija();
    }

    public static Kontroler getInstanca() {
        if(instanca == null){
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ArrayList<String> getListaKategorija() {
        return listaKategorija;
    }

    
    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public List<Zaposleni> getListaZaposlenih() {
        return listaZaposlenih;
    }

    public void setListaZaposlenih(List<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }

    private ArrayList<String> napraviListuKategorija() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Invalid");
        lista.add("Srednjoškolac");
        lista.add("Student");
        lista.add("Penzioner");
        return lista;
        
    }
    
    public ServerskiOdgovor prijaviZaposlenog(Zaposleni z) throws Exception {
        PrijavaZaposlenog operacija =  new PrijavaZaposlenog();
        return operacija.izvrsiTransakciju(z);
    }

    public boolean proveriPrijavljenostZaposlenog(Zaposleni zaposleni) {
        for (Zaposleni z : listaZaposlenih) {
            if(zaposleni.equals(z)){
                return true;
            }
            
        }
        return false;
    }

    public ServerskiOdgovor kreirajClanskuKartu(ClanskaKarta ck) throws Exception {
         KreirajClanskuKartu operacija=  new KreirajClanskuKartu();
         return operacija.izvrsiTransakciju(ck);       
    }

    public ServerskiOdgovor vratiListuDela(Delo d) throws Exception {
        VratiListuDela operacija = new VratiListuDela();
        return operacija.izvrsiTransakciju(d);
    }

      public ServerskiOdgovor kreirajIzlozbu(Izlozba izl) throws Exception {
          KreirajIzlozbu operacija = new KreirajIzlozbu();
          return operacija.izvrsiTransakciju(izl);
      }
      
  /*    public ServerskiOdgovor kreirajPostavku(ArrayList<Postavka> lista) throws Exception {
         KreirajPostavku operacija=  new KreirajPostavku();
         return operacija.izvrsiTransakciju(lista); 
    }*/

    public ServerskiOdgovor vratiIzlozbaID(Izlozba i) throws Exception {
         VratiIzlozbaID operacija=  new VratiIzlozbaID();
         return operacija.izvrsiTransakciju(i);        
    }

    public ServerskiOdgovor vratiListuIzlozbi(Izlozba izlozba) throws Exception {
        VratiListuIzlozbi operacija = new VratiListuIzlozbi();
        return operacija.izvrsiTransakciju(izlozba);
    }

    public ServerskiOdgovor kreirajUlaznicu(Ulaznica ulaznica) throws Exception {
        KreirajUlaznicu operacija = new KreirajUlaznicu();
        return operacija.izvrsiTransakciju(ulaznica);
    }

    public ServerskiOdgovor kreirajVodica(Vodic vodic) throws Exception {
        KreirajVodica operacija = new KreirajVodica();
        return operacija.izvrsiTransakciju(vodic);
    }

    public ServerskiOdgovor vratiListuVodica(Vodic v) throws Exception {
        VratiListuVodica operacija = new VratiListuVodica();
        return operacija.izvrsiTransakciju(v);
    }

    public ServerskiOdgovor nadjiVodice(Vodic v) throws Exception {
        NadjiVodica operacija = new NadjiVodica();
        return operacija.izvrsiTransakciju(v);
    }

    public ServerskiOdgovor vratiListuClanskihKarata(ClanskaKarta clanska) throws Exception {
        VratiListuClanskihKarata operacija = new VratiListuClanskihKarata();
        return operacija.izvrsiTransakciju(clanska);
    }

    public ServerskiOdgovor pretraziClanskeKarte(ClanskaKarta cl) throws Exception {
        PretraziClanskeKarte operacija = new PretraziClanskeKarte();
        return operacija.izvrsiTransakciju(cl);
    }

    public ServerskiOdgovor vratiClanskuKartu(ClanskaKarta cl1) throws Exception {
        VratiClanskuKartu operacija = new VratiClanskuKartu();
        return operacija.izvrsiTransakciju(cl1);
    }

    public ServerskiOdgovor zapamtiClanskuKartu(ClanskaKarta cl2) throws Exception {
        ZapamtiClanskuKartu operacija = new ZapamtiClanskuKartu();
        return operacija.izvrsiTransakciju(cl2);
    }

    public ServerskiOdgovor nadjiIzlozbe(Izlozba iz) throws Exception {
        NadjiIzlozbe operacija = new NadjiIzlozbe();
        return operacija.izvrsiTransakciju(iz);
    }

    public ServerskiOdgovor ucitajIzlozbu(Izlozba izl) throws Exception {
        UcitajIzlozbu operacija = new UcitajIzlozbu();
        return operacija.izvrsiTransakciju(izl);
    }

    public ServerskiOdgovor obrisiIzlozbu(Izlozba iz2) throws Exception {
        ObrisiIzlozbu operacija = new ObrisiIzlozbu();
        return operacija.izvrsiTransakciju(iz2);
    }

    public ServerskiOdgovor ucitajDela(Izlozba iz3) throws Exception {
        UcitajDela operacija = new UcitajDela();
        return operacija.izvrsiTransakciju(iz3);    }

  


  
    
    
    
}
