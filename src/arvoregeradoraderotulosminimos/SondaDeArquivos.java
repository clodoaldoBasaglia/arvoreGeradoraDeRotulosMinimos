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
import java.util.Iterator;
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
        ArrayList<String> pastas = new ArrayList<>();
        ArrayList<String> arquivos = new ArrayList<>();
        String comando = "ls " + pontoInicio;
        try {
            Process processo = Runtime.getRuntime().exec(comando);
            BufferedReader leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha = "";
            while ((linha = leitor.readLine()) != null) {
                pastas.add(linha);

            }
            for (String pasta : pastas) {
                comando = "ls " + pontoInicio + pasta + "/";
                arquivos = new ArrayList<>();
                processo = Runtime.getRuntime().exec(comando);
                leitor = new BufferedReader(new InputStreamReader(processo.getInputStream()));
                linha = "";
                while ((linha = leitor.readLine()) != null) {
                    arquivos.add(linha);
                }
                arvoreDiretorio.put(pasta, arquivos);
//                printMapa(arvoreDiretorio);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SondaDeArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arvoreDiretorio;
    }

    private String colocaContraBarra(String linha) {
        String replace = linha.replace(" ", "\\ ");
        return replace;
    }

    private void printMapa(Map<String, ArrayList<String>> arvoreDiretorio) {
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = arvoreDiretorio.entrySet().iterator();
        while(iterator.hasNext()){
            String chave = iterator.next().getKey();
            ArrayList<String> get = arvoreDiretorio.get(chave);
            for (String string : get) {
                System.out.println("Chave: "+chave+ " Arquivo: "+string);
            }
        }
    }

}
