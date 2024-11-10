/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAB04;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class puertosLocales {
    
    public void verificaPuerto(int numPuerto){
        try {
            ServerSocket server = new ServerSocket(numPuerto);
            
        } catch (IOException ex) {
            System.out.println("El Puerto "+numPuerto+" Abierto...." + ex.getMessage()); // otra aplicacion la esta usando
        }    
    }
   
    public void escaneaPuertos(){
        int puerto = 1;
        while (puerto <= 65535){
         verificaPuerto(puerto); 
         puerto++;
        }    
    }
    
    public static void main(String[] args) {
        
        puertosLocales pl1 = new puertosLocales();
//        pl1.verificaPuerto(3307);
        pl1.escaneaPuertos();
        
    }        
}
