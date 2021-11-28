/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eschat;

/**
 *
 * @author russo_edoardo
 */
public class AssegnaNickname {

   CConnesione c;
   
   public AssegnaNickname(){
       c= new CConnesione();
   }

    public AssegnaNickname(CConnesione c) {
        this.c = c;
    }

   public void controllonickname(){
       if (c.getNickname_destinatario()==" ") {
           c.chiusuraConnessione();
       }
   }
   
}
