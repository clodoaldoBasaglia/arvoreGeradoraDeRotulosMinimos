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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clodoaldo
 */
public class LerArquivo {

    String lerArquivo(String caminho) {
        try {
            List<String> linhas = new ArrayList<String>();
            File arq = new File(caminho);
            BufferedReader bfr = null;
            bfr = new BufferedReader(new FileReader(arq));
            try {
                while(bfr.readLine()!=null){
                    String teste = bfr.readLine();
                    System.out.println(teste);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(LerArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return " ";
    }

}
