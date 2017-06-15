/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a968692
 */
public class AGRMMVCADL {

    public static void main(String[] args) {
        //principal
        String caminho = "/home/todos/alunos/cm/a968692/Documentos/grafos/instancias/";
        SondaDeArquivos sdd = new SondaDeArquivos();
        LerArquivo la = new LerArquivo();
        Map<String, ArrayList<String>> sondaDeArquivos = sdd.sondaDeArquivos(caminho);
        System.out.println(sondaDeArquivos.size());
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivos.entrySet().iterator();
        Map<String, ArrayList<Grafo>> mapaGrafos = new HashMap<>();
        while (iterator.hasNext()) {
            String chave = iterator.next().getKey();
            System.out.println(chave);
            ArrayList<String> get = sondaDeArquivos.get(chave);
            
            for (String string : get) {
                try {
                    System.out.println(get);
                    ArrayList<Grafo> leArquivo = la.leArquivo(caminho + chave + "/" + string);
                    mapaGrafos.put(chave + ":" + string, leArquivo);
                } catch (IOException ex) {
                    Logger.getLogger(AGRMMVCADL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println(mapaGrafos.size());
    }

}
