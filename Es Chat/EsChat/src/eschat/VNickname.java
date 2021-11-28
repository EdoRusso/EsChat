/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eschat;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.SocketException;

/**
 *
 * @author Edo
 */
public class VNickname extends Thread{
    private Server server = null;
    private boolean run;

    public VNickname() throws SocketException {
        server = Server.getInstance();
        run = true;
    }

    @Override
    public void run() {
        while (run) {
            //Ricevo i messaggi
            CMessaggio m = null;
            try {
                m = server.receiveMessage();

            } catch (IOException ex) {
                Logger.getLogger(VNickname.class.getName()).log(Level.SEVERE, null, ex);
            }

            CCondivisa d = CCondivisa.getInstance();
            if (d.getConnessione() != null) {
            }
            try {
                //Elaboro i messaggi
                Elabora.elaborazione(m);
            } catch (SocketException ex) {
                Logger.getLogger(VNickname.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
