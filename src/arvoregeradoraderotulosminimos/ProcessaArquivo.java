/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clodoaldo
 */
public class ProcessaArquivo {

    public String abreArquivo(File arq) {
        String texto = "";
        try {
            BufferedReader buff = new BufferedReader(new FileReader(arq));
            try {
                while ( buff.readLine() != null) {
//                    System.out.println(buff.readLine());
                    texto  = buff.readLine();
                    texto += texto;
                }
            } catch (IOException ex) {
                Logger.getLogger(ProcessaArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProcessaArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texto;
    }

}
