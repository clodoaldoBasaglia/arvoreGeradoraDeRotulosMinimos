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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author a968692
 */
public class AGRMMVCADL {

    public static void main(String[] args) {
        //principal
        String caminho = "/home/todos/alunos/cm/a968692/Documentos/grafos/instancias/";
        ArrayList<Integer> rotulosUtilizados = new ArrayList<>();
        SondaDeArquivos sdd = new SondaDeArquivos();
        LerArquivo la = new LerArquivo();
        Map<String, ArrayList<String>> sondaDeArquivos = sdd.sondaDeArquivos(caminho);
        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivos.entrySet().iterator();
        Map<String, ArrayList<Grafo>> mapaGrafos = new HashMap<>();

        JSONArray objPastas = new JSONArray();
        JSONObject objPasta = new JSONObject();

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
        System.out.println(mapaGrafos.size());
        Iterator<Map.Entry<String, ArrayList<Grafo>>> iteratorMapaGrafos = mapaGrafos.entrySet().iterator();

        int qtdGrafos = 0;
        while (iteratorMapaGrafos.hasNext()) {
            String key = iterator.next().getKey();
            objPasta.put("Diretorio", key);
            ArrayList<Grafo> get = mapaGrafos.get(key);
            for (Grafo graph : get) {
                //Se retornar nulo significa que o grafo não é conexo
                if ((rotulosUtilizados = MVCA.MVCA(graph)) != null) {
                    funcObjetiva += rotulosUtilizados.size();
                    qtdGrafos++;
                }
            }
            if (funcObjetiva != 0) {
                funcObjetiva = funcObjetiva / qtdGrafos;
            }

        }
        long startTime = System.currentTimeMillis();
        String pasta = "group2";
        //Fazer isso uma vez para cada pasta

        //Fazer o laço de grafos para cada arquivo dentro da pasta
        long endTime = System.currentTimeMillis();

        //fazer isso para cada Arquivo dentro da pasta X
        //Se a func objetivo for 0, quer dizer que todos os grafos do arquivo nao sao conexos.
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
