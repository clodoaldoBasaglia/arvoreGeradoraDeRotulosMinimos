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
public class ArvoreGeradoraDeRotulosMinimosTeste {

    /**
     * @param args the command line arguments
     */
    private static final String caminho2 = "HDGraph20_20.txt";

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<Integer> rotulosUtilizados = new ArrayList<>();
        ArrayList<Grafo> grafos = LerArquivo.leArquivo(caminho2);

        JSONArray objPastas = new JSONArray();
        JSONObject objPasta = new JSONObject();

        float funcObjetiva = 0;
        long startTime = System.currentTimeMillis();

        //Fazer isso uma vez para cada pasta
        String pasta = "group2";
        objPasta.put("Diretorio", pasta);
        int qtdGrafos = 0;

        //Fazer o laço de grafos para cada arquivo dentro da pasta
        for (Grafo graph : grafos) {
            //Se retornar nulo significa que o grafo não é conexo
            if ((rotulosUtilizados = MVCA.MVCA(graph)) != null) {
                funcObjetiva += rotulosUtilizados.size();
                qtdGrafos++;
            }
        }
        long endTime = System.currentTimeMillis();
//        System.out.println(funcObjetiva + " e " + qtdGrafos);

        //fazer isso para cada Arquivo dentro da pasta X
        //Se a func objetivo for 0, quer dizer que todos os grafos do arquivo nao sao conexos.
        if (funcObjetiva != 0) {
            funcObjetiva = funcObjetiva / qtdGrafos;
        }

        objPasta.put("Arquivos", AGRMOutput((funcObjetiva), caminho2, qtdGrafos, (endTime - startTime)));

        //Apos gravar todos os arquivos da pasta X, gravar em Pastas e fazer o mesmo para a proxima pasta, até não existir mais pastas
        objPastas.add(objPasta);

        //Apos terminar todas as Pastas e arquivos, gravar Json de todos os resultados
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
