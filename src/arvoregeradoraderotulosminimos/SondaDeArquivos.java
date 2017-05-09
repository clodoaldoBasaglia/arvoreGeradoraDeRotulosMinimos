/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clodoaldo
 */
public class SondaDeArquivos {

    public Map<String, ArrayList<String>> sondaDeArquivos(String pontoInicio) {
        Map<String, ArrayList<String>> arvoreDiretorio = new HashMap<>();
        String[] comando = {"xterm","-e" ,"cd"," /home/clodoaldo/Documentos/APS.Teoria.dos.Grafos.2017.1/instancias/"};
        try {
            Process processo = new ProcessBuilder(comando).start();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
            String linha = "";
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException ex) {
            Logger.getLogger(SondaDeArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arvoreDiretorio;
    }
}
