/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eschat;

import java.awt.Frame;

/**
 *
 * @author Edo
 */
public class CCondivisa {
     private String nickname = "";
    private Frame frame = null;
    private static CCondivisa instance = null;
    private CConnesione connessione = null;
    private int ip; 
    private CCondivisa(){
        
    }
    
    public static CCondivisa getInstance(){
        if(instance == null){
            synchronized(CCondivisa.class){
                if(instance == null)
                    instance = new CCondivisa();
            }
        }
        return instance;
    }

    public String getMyNickname() {
        return nickname;
    }

    public void setMyNickname(String my_nickname) {
        this.nickname = my_nickname;
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public CConnesione getConnessione() {
        return connessione;
    }

    public void setConnessione(CConnesione connessione) {
        this.connessione = connessione;
    }

    public int getRemote_ip() {
        return ip;
    }

    public void setRemote_ip(int remote_ip) {
        this.ip = remote_ip;
    }

    
    
    
    
    
    
}
