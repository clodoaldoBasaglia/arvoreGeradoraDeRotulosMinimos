/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author clodoaldo
 */
public class ArvoreGeradoraDeRotulosMinimos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long inicio = System.currentTimeMillis() % 1000;
        LerArquivo la = new LerArquivo();
        String caminho = "/home/clodoaldo/Documentos/APS.Teoria.dos.Grafos.2017.1/instancias/";
        SondaDeArquivos sdd = new SondaDeArquivos();
        File arq = null;
        Map<String, ArrayList<String>> sondaDeArquivos = sdd.sondaDeArquivos(caminho);
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivos.entrySet().iterator();
        ArrayList<File> arrayDeArquivo = new ArrayList<>();
        while (iterator.hasNext()) {
            String chave = iterator.next().getKey();
            ArrayList<String> get = sondaDeArquivos.get(chave);
            for (String string : get) {
//                System.out.println(caminho + chave + "/" + string);
                arq = la.LerArquivo(caminho + chave + "/" + string);
                arrayDeArquivo.add(arq);
            }
        }
        long fim = System.currentTimeMillis() % 1000;
        for (File file : arrayDeArquivo) {
            
        }
    }

}
