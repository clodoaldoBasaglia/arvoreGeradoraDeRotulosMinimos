/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;


/**
 *
 * @author clodoaldo
 */
public class ArvoreGeradoraDeRotulosMinimos {

    /**
     * @param args the command line arguments
     */
    private static final String caminho2 = "HDGraph20_20.txt";
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<Integer> rotulosUtilizados = new ArrayList<>();
        ArrayList<Grafo> grafos = LerArquivo.leArquivo(caminho2);
        float funcObjetiva = 0;
        long startTime = System.currentTimeMillis();
        for(Grafo graph : grafos){
            rotulosUtilizados = MVCA.MVCA(graph);
            funcObjetiva += rotulosUtilizados.size();           
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcObjetiva + " e " + grafos.size()); 
        AGRMOutput((funcObjetiva/grafos.size()),"HD500", caminho2, (endTime - startTime));
        
//        LerArquivo la = new LerArquivo();
//        String caminho = "/home/clodoaldo/Documentos/APS.Teoria.dos.Grafos.2017.1/instancias/";
//        SondaDeArquivos sdd = new SondaDeArquivos();
//        Map<String, ArrayList<String>> sondaDeArquivos = sdd.sondaDeArquivos(caminho);
//        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivos.entrySet().iterator();
//        while (iterator.hasNext()) {
//            String chave = iterator.next().getKey();
//            ArrayList<String> get = sondaDeArquivos.get(chave);
//            for (String string : get) {
////                System.out.println(caminho+chave+"/"+string);
//                File arquivo = la.LerArquivo(caminho);
//                System.out.println(arquivo.getTotalSpace());
//            }
//        }

    }
    
    public static void AGRMOutput (float rotulosUtilizados, String pasta, String arquivo, long time) throws IOException{
        
        
        JSONArray objGrafos = new JSONArray();
                      
        JSONObject objGrafo = new JSONObject();
        
        objGrafo.put("Pasta", pasta);
        objGrafo.put("Arquivo", arquivo);
        objGrafo.put("Media de Rotulos Minimos", rotulosUtilizados);
        objGrafo.put("Tempo de execucao", time + " ms");
        
        objGrafos.add(objGrafo);
    
    // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("Resultados.txt")) {
            
//              String teste = objRotas.toString();
            //Identar Output modo Pretty Print  
            Gson gson = new GsonBuilder().setPrettyPrinting().create();         
            String teste = gson.toJson(objGrafos);
            
            //file.write(objRotas.toJSONString());
            file.write(teste);
            
        }

         
        }

}
