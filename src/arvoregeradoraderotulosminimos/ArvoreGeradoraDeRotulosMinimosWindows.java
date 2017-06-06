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
 * @author Clodoaldo
 */
public class ArvoreGeradoraDeRotulosMinimosWindows {

    public static void main(String[] args) {
        LerArquivo la = new LerArquivo();
//        String caminho = "/home/clodoaldo/Documentos/APS.Teoria.dos.Grafos.2017.1/instancias/";
//        String caminho = "/home/todos/alunos/cm/a968692/Documentos/grafos/instancias/";
        String caminho = "C:\\Users\\Clodoaldo\\Documents\\UTFPR\\TeoriaGrafos\\APS\\instancias\\";
//        SondaDeArquivos sdd = new SondaDeArquivos();
        SondaDeArquivosWindows sddW = new SondaDeArquivosWindows();
//        System.out.println(sddW.SondaDeArquivosWindows(caminho).size());
        Map<String, ArrayList<String>> sondaDeArquivosWindows = sddW.SondaDeArquivosWindows(caminho);

        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivosWindows.entrySet().iterator();
        Map<String, ArrayList<Integer[][]>> mapaGrafos = new HashMap<>();
        while (iterator.hasNext()) {
            String chave = iterator.next().getKey();
            ArrayList<String> get = sondaDeArquivosWindows.get(chave);
            for (String string : get) {
                try {
                    ArrayList<Integer[][]> leArquivo = la.leArquivo(caminho + chave + "/" + string);
                    mapaGrafos.put(chave + ":" + string, leArquivo);
                } catch (IOException ex) {
                    Logger.getLogger(ArvoreGeradoraDeRotulosMinimos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int con = 0;
        for (Map.Entry<String, ArrayList<Integer[][]>> entry : mapaGrafos.entrySet()) {
            String chave = entry.getKey();
            ArrayList<Integer[][]> valor = entry.getValue();
            for (Integer[][] integerses : valor) {
                System.out.println(chave + integerses[0]);
                con++;
            }
            System.out.println(con);
        }

    }

}
