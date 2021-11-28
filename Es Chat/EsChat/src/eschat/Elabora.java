/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eschat;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

/**
 *
 * @author Edo
 */
public class Elabora {
     public static void elaborazione(CMessaggio messaggio)throws SocketException {
         CCondivisa c = CCondivisa.getInstance();
        Server s = Server.getInstance();

        if (c.getConnessione() == null) {
            c.setConnessione(new CConnesione());
        }

        String comando = messaggio.getComando();

        if (comando.equals("c") && !c.getConnessione().getConnessioneAperta()) {
            AccettaConnessione.acceptConnection(messaggio);
        } else if ("c".equals(comando) && c.getConnessione().getConnessioneAperta()) {
            //rifiuto la connessione di default perchè ne ho già aperta un'altra
            CMessaggio risposta = new CMessaggio();
            try {
                s.sendMessage(messaggio);
            } catch (IOException ex) {
                Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Tentativo di connessione mentre è già stata stabilita un'altra connessione, il tentativo è stato annullato");
        } else {
            InetAddress source_address = messaggio.getPacchetto().getAddress();
            if (source_address.equals(c.getRemote_ip())) {
                switch (comando) {
                    case "e":
                        JOptionPane.showMessageDialog(c.getFrame(), "La connessione è stata interrotta dall'altro host");
                        c.getConnessione().chiusuraConnessione();
                        break;
                }
            } else {
                if (!messaggio.getComando().equals("e")) {
                    CMessaggio risposta = new CMessaggio();
                    try {
                        s.sendMessage(risposta);
                    } catch (IOException ex) {
                        Logger.getLogger(Elabora.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    System.out.println("Tentativo di invio messaggio da un host non connesso");
                }

            }
        }

    }
     

}
