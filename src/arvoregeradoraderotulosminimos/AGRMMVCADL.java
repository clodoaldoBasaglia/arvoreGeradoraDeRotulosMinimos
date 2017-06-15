/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

import static arvoregeradoraderotulosminimos.ArvoreGeradoraDeRotulosMinimosTeste.AGRMOutput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author a968692
 */
public class AGRMMVCADL {

    public static void main(String[] args) throws IOException {
        //principal
        String caminho = "/home/clodoaldo/UTFPR/GRAFOS/instancias/";

        ArrayList<Integer> rotulosUtilizados = new ArrayList<>();
        SondaDeArquivos sdd = new SondaDeArquivos();

        LerArquivo la = new LerArquivo();
        Map<String, ArrayList<String>> sondaDeArquivos = sdd.sondaDeArquivos(caminho);

        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivos.entrySet().iterator();
        Map<String, ArrayList<Grafo>> mapaGrafos = new HashMap<>();

        JSONArray objPastas = new JSONArray();
        JSONObject objPasta = new JSONObject();
        long startTime = 0;
        long endTime = 0;
        float funcObjetiva = 0;

//        System.out.println(funcObjetiva + " e " + qtdGrafos);
        while (iterator.hasNext()) {
            String chave = iterator.next().getKey();
            ArrayList<String> get = sondaDeArquivos.get(chave);
            for (String string : get) {
                try {
                    ArrayList<Grafo> leArquivo = la.leArquivo(caminho + chave + "/" + string);
                    mapaGrafos.put(chave + ":" + string, leArquivo);
                } catch (IOException ex) {
                    Logger.getLogger(AGRMMVCADL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Iterator<Map.Entry<String, ArrayList<Grafo>>> iteratorMapaGrafos = mapaGrafos.entrySet().iterator();
        System.out.println((iteratorMapaGrafos.hasNext() ? "sim" : "não"));
        int qtdGrafos = 0;
        for (Map.Entry<String, ArrayList<Grafo>> entry : mapaGrafos.entrySet()) {
            String key = entry.getKey();
            ArrayList<Grafo> value = entry.getValue();
            objPasta.put("Diretorio", key);
            System.out.println("Calculando para: " + key);
            for (Grafo graph : value) {
                //Se retornar nulo significa que o grafo não é conexo
                startTime = System.currentTimeMillis();
                rotulosUtilizados = MVCA.MVCA(graph);
                endTime = System.currentTimeMillis();
                if (rotulosUtilizados != null) {
                    funcObjetiva += rotulosUtilizados.size();
                    qtdGrafos++;
                }
            }
            if (funcObjetiva != 0) {
                funcObjetiva = funcObjetiva / qtdGrafos;
            }
            try {
                objPasta.put("Arquivos", AGRMOutput((funcObjetiva), key, qtdGrafos, (endTime - startTime)));
                objPastas.add(objPasta);
            } catch (IOException ex) {
                Logger.getLogger(AGRMMVCADL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try (FileWriter file = new FileWriter("Resultados.txt")) {
            //Identar Output modo Pretty Print	
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String teste = gson.toJson(objPastas);

            //file.write(objRotas.toJSONString());
            file.write(teste);
        }

    }

    public static JSONArray AGRMOutput(float rotulosUtilizados, String arquivo, int qtdConexos, long time) throws IOException {

        JSONArray objArquivos = new JSONArray();
        JSONObject objArquivo = new JSONObject();
        objArquivo.put("Quantidade de grafos conexos", qtdConexos);
        objArquivo.put("Arquivo", arquivo);
        objArquivo.put("Media de Rotulos Minimos", rotulosUtilizados);
        objArquivo.put("Tempo de execucao", time + " ms");

        objArquivos.add(objArquivo);

        return objArquivos;
    }

}
