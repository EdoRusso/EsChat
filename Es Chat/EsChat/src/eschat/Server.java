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
public class Server {
    private DatagramSocket pacchetto;
    private int nporta = 2003;
    private static Server instance = null;
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
        pacchetto=null;
        nporta=0;
        
    }
    public static Server getInstance() throws SocketException {
        if (instance == null) {
            synchronized (Server.class) {
                if (instance == null) {
                    instance = new Server();
                }
            }
        }
        return instance;
    }
    public void sendMessage(CMessaggio messaggio) throws IOException{
        String stringa_messaggio = messaggio.toCSV();
        byte[] buffer_invio = stringa_messaggio.getBytes();
        DatagramPacket pacchetto_invio = new DatagramPacket(buffer_invio, buffer_invio.length);
        CCondivisa d = CCondivisa.getInstance();
        pacchetto_invio.setAddress(messaggio.getIndirizzo_ip());
        pacchetto_invio.setPort(2003);
        server_socket.send(pacchetto_invio);
    }
        public CMessaggio receiveMessage() throws IOException{
        byte[] buffer_ricezione = new byte[1500];
        DatagramPacket pacchetto_ricezione = new DatagramPacket(buffer_ricezione, buffer_ricezione.length);
        pacchetto.receive(pacchetto_ricezione);
        CMessaggio messaggio = CMessaggio.loadFromDatagramPacket(pacchetto_ricezione);
        messaggio.setIndirizzo_ip(pacchetto_ricezione.getAddress().toString());
        return messaggio;
    }
}
