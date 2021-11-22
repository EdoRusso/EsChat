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
    private String messaggio = null;

    public CMessaggio() {
        comandoinvio = "";
        messaggio = "";
    }

    public CMessaggio(String comando, String corpo_messaggio) {
        this.comandoinvio = comando;
        this.messaggio = corpo_messaggio;
    }
    
    public String getComando() {
        return comandoinvio;
    }

    public void setComando(String comando) {
        this.comandoinvio = comando;
    }

    public String getCorpo_messaggio() {
        return messaggio;
    }

    public void setCorpo_messaggio(String corpo_messaggio) {
        this.messaggio = corpo_messaggio;
    }
    public String loadFromCSV(String stringa_csv){
        String comandoinvio;
        int pos = stringa_csv.indexOf(";");
        comandoinvio = stringa_csv.substring(0, pos);
        messaggio = stringa_csv.substring(pos + 1, stringa_csv.length());
        return messaggio;
    }
    
    public String toCSV(){
        String csv_string = comandoinvio + ";" + messaggio+";";
        return csv_string;
    }



}
