/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.OpstiDomenskiObjekat;
import domen.Zaposleni;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hasan
 */
public class ModelTabeleZaposleni extends AbstractTableModel{

    ArrayList<OpstiDomenskiObjekat> listaZaposlenih;
    String[] kolone = new String[]{"ID","Ime i prezime","Korisnicko ime"};

    public ModelTabeleZaposleni() {
    }

    public ModelTabeleZaposleni(ArrayList<OpstiDomenskiObjekat> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }
    
    
    
    @Override
    public int getRowCount() {
        return listaZaposlenih.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int red, int kol) {
        Zaposleni z = (Zaposleni) listaZaposlenih.get(red);
        switch(kol){
            case 0: return z.getZaposleniID();
            case 1: return z.getImePrezime();
            case 2: return z.getKorisnickoIme();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }
    

    public void dodajZaposlenog(Zaposleni zap) {
        listaZaposlenih.add(zap);
        fireTableDataChanged();
    }

    public void obrisiSveZaposlene() {
        listaZaposlenih.clear();
        fireTableDataChanged();
    }
    
    
}
