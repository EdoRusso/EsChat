/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eschat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Edo
 */
public class CConnesione {
    public String nickname_destinatario;
    public boolean apri_connesione;
    public String ip_destinatario;
    public int nporta;

    public CConnesione() {
        nickname_destinatario="";
        apri_connesione=true;
        ip_destinatario="";
        nporta=2003;
    }

    public CConnesione(String nickname_destinatario, boolean apri_connesione, String ip_destinatario, int nporta) {
        this.nickname_destinatario = nickname_destinatario;
        this.apri_connesione = apri_connesione;
        this.ip_destinatario = ip_destinatario;
        this.nporta = nporta;
    }
    public String aperturaConnessione()throws SocketException, IOException{
        DatagramSocket server = new DatagramSocket(nporta);

        byte[] buffer = new byte[1500];
        DatagramPacket pacchetto = new DatagramPacket(buffer, buffer.length);
        server.receive(pacchetto);
        String indirizzoRemoto = pacchetto.getAddress().toString();
        int portaRemota = pacchetto.getPort();
        byte[] datiRicevuti = pacchetto.getData();
        String messaggio = new String(datiRicevuti);
        System.out.println("Server: indirizzo: " + indirizzoRemoto);
        System.out.println("Server: Porta:" + portaRemota);
        System.out.println("Server: Messaggio: " + messaggio);
        return messaggio;

    }
    public void chiusuraConnessione(){
        apri_connesione=false;
        ip_destinatario=null;
        nickname_destinatario=null;
    }
    
}
