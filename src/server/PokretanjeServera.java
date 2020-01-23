/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hasan
 */
public class PokretanjeServera extends Thread{

    ServerskaForma sf;
    ArrayList<ObradaKlijentskihZahteva> lista;

    public PokretanjeServera(ServerskaForma sf) {
        this.sf = sf;
        lista = new ArrayList<>();
    }

    

    public ArrayList<ObradaKlijentskihZahteva> getLista() {
        return lista;
    }

    public void setLista(ArrayList<ObradaKlijentskihZahteva> lista) {
        this.lista = lista;
    }
    
    
    
    @Override
    public void run() {

        try {
            ServerSocket ss = new ServerSocket(9000);
            sf.pokreniServer();
            ZaustavljanjeNiti zn = new ZaustavljanjeNiti(this, ss);
            zn.start();
            System.out.println("Server je pokrenut.");
            
            while(!isInterrupted()){
                Socket s = ss.accept();
                System.out.println("Klijent se povezao.");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(lista,s);
                okz.start();
                lista.add(okz);

            }

            
        } catch (IOException ex) {
            sf.zaustaviServer();
            System.out.println("Server je zaustavljen.");
        }

    }

    public void ugasi() throws IOException {
        for (ObradaKlijentskihZahteva okz : lista) {
           okz.s.close();
        }
    }

   
    
    
    
    
}
