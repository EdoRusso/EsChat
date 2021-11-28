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
import javax.swing.JOptionPane;

/**
 *
 * @author Edo
 */
public class AccettaConnessione extends Thread{
      public static void acceptConnection(CMessaggio messaggio) throws IOException {
        CCondivisa d = CCondivisa.getInstance();
        if (!d.getConnessione().isApri_connesione()) {

            String ip_string = messaggio.getPacchetto().getAddress().toString();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialog_result = JOptionPane.showConfirmDialog(d.getFrame(), "Accettare connessione da parte di " + ip_string, "Richiesta connessione", dialogButton);

            Server s = null;
            try {
                s = Server.getInstance();
            } catch (SocketException ex) {
                Logger.getLogger(AccettaConnessione.class.getName()).log(Level.SEVERE, null, ex);
            }
            CMessaggio messaggio_risposta = null;
            if (dialog_result == 0) {
                messaggio_risposta = new CMessaggio();
                CConnesione connessione = new CConnesione();
                connessione.getNickname_destinatario();
                connessione.aperturaConnessione();
                d.setConnessione(connessione);
            } else {
                //Rifiuto la connessione
                //rispondo con n
                messaggio_risposta = new CMessaggio();
            }
            try {
                s.aperturaConnessione();
                //System.out.println(messaggio_risposta.getIndirizzo_ip());
            } catch (IOException ex) {
                Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                Server s = Server.getInstance();
                s.aperturaConnessione();
            } catch (IOException ex) {
                Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
