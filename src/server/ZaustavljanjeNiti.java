/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hasan
 */
public class ZaustavljanjeNiti extends Thread{
    PokretanjeServera ps;
    ServerSocket ss;
    boolean kraj = false;

    public ZaustavljanjeNiti(PokretanjeServera ps, ServerSocket ss) {
        this.ps = ps;
        this.ss = ss;
    }

    @Override
    public void run() {
        while(!kraj){
            if(ps.isInterrupted()){
                try {
                    ss.close();
                    kraj = true;
                } catch (IOException ex) {
                    Logger.getLogger(ZaustavljanjeNiti.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZaustavljanjeNiti.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
