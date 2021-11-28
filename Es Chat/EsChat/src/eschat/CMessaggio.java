/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eschat;

import java.net.DatagramPacket;

/**
 *
 * @author Edo
 */
public class CMessaggio {
    private String comandoinvio = null;
    private String corpo_messaggio=null;
    private DatagramPacket pacchetto = null;
    private String indirizzo_ip;
    public CMessaggio() {
        comandoinvio = "";
    }

    public CMessaggio(String comando, String corpo_messaggio) {
        this.comandoinvio = comando;
    }
    
    public String getComando() {
        return comandoinvio;
    }

    public void setComando(String comando) {
        this.comandoinvio = comando;
    }

    public String getCorpo_messaggio() {
        return corpo_messaggio;
    }

    public void setCorpo_messaggio(String corpo_messaggio) {
        this.corpo_messaggio = corpo_messaggio;
    }
    public static CMessaggio loadFromCSV(String stringa_csv){
        String comando = "";
        String corpo_messaggio = "";
        
        int pos = stringa_csv.indexOf(';');
        comando = stringa_csv.substring(0, pos);
        corpo_messaggio = stringa_csv.substring(pos + 1, stringa_csv.length()-1);
        
        CMessaggio m = new CMessaggio(comando, corpo_messaggio);
        return m;
    }
    
    public String toCSV(){
        String csv_string = comandoinvio + ";" + corpo_messaggio+";";
        return csv_string;
    }
public static CMessaggio loadFromDatagramPacket(DatagramPacket pacchetto){
        byte[] buffer = pacchetto.getData();
        String stringa_ricevuta = new String(buffer,0,pacchetto.getLength());
        CMessaggio m = loadFromCSV(stringa_ricevuta);
        m.setPacchetto(pacchetto);
        return m;
    }
 
    public DatagramPacket getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(DatagramPacket pacchetto) {
        this.pacchetto = pacchetto;
    }

    public String getIndirizzo_ip() {
        return indirizzo_ip;
    }

    public void setIndirizzo_ip(String indirizzo_ip) {
        this.indirizzo_ip = indirizzo_ip;
    }
    
    
    


}
