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

import javax.lang.model.type.NullType;

/**
 *
 * @author luisfernando
 */
public class LerArquivo {

//   
    public static ArrayList <Grafo> leArquivo(String caminho) throws IOException {
        File arquivo = new File(caminho);
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        if (!arquivo.exists()) {
            throw new IOException("Arquivo nao encontrado");
        }

        //Linha 1 do arquivo
        String linha = br.readLine();
        String[] s = linha.split(" ");

        int vertices = Integer.parseInt(s[0]);
        int rotulos = Integer.parseInt(s[1]);        
        int verticesCoresVisitas[] = inicializaCorVertices(vertices);

        int indice = vertices;
//        System.out.println("vertices: " + indice);
        
        ArrayList <Grafo> Grafos = new ArrayList<>();
        Grafo Graph = new Grafo();
        Integer[][] matrizAdjacencia = new Integer[vertices][vertices];
               
        int i = 0;
        int aux = 0;
        int pos = 0;

        //Enquanto houver algo a ser lido, continuar
        while (((linha = br.readLine()) != null)) {
            //Caso um grafo tenha sido lido por inteiro
            if (i % vertices == 0) {
                //Caso não seja a primeira execução, para não gerar um grafo totalmente nulo
                if (aux != 0) {
                    Graph.setMatrizAdjacencia(matrizAdjacencia);
                    Graph.setQuantidadeDeRotulos(rotulos);
                    Graph.setQuantidadeVertices(vertices);
                    Graph.setCorVertices(verticesCoresVisitas);
                    Grafos.add(Graph);
                    matrizAdjacencia = new Integer[vertices][vertices];
                    Graph = new Grafo();
                }
                i = 0;
            }
            s = linha.split(" ");
            
            //Preenchendo a matriz. Matriz[ultimaLinha até a primeira][Primeiracoluna até a ultima]
            for (int j = 0; j < s.length; j++) {
                //Caso não seja uma linha em branco
                if (!s[j].equals("")) {
                
                    matrizAdjacencia[indice - i - 1][j] = Integer.parseInt(s[j]);
                        //Espelhando a matriz, pra criar a diagonal superior da Matriz(Grafo não dirigido)
                        matrizAdjacencia[j][indice - i - 1] = Integer.parseInt(s[j]);
                }
            }
            //Setando a diagonal principal. Sem aresta pra outro vértice
            matrizAdjacencia[indice - i - 1][s.length] = rotulos;
            matrizAdjacencia[0][0] = rotulos;
            
            aux++;
            i++;
        }
        Graph = new Grafo();
        Graph.setMatrizAdjacencia(matrizAdjacencia);
        Graph.setQuantidadeDeRotulos(rotulos);
        Graph.setQuantidadeVertices(vertices);
        Graph.setCorVertices(verticesCoresVisitas);
        Grafos.add(Graph);       
        
        br.close();
        fr.close();
        
//        for(Grafo graph : Grafos)
//            printMatriz(graph, vertices);
        
        return Grafos;
}

    public static int[] inicializaCorVertices(int qtdVertices){
        int verticesCoresVisitas[] = new int[qtdVertices]; 
        for (int i = 0; i < qtdVertices; i++) {
            verticesCoresVisitas[i] = -1;
        }
        return verticesCoresVisitas;
    }
    
    public static void printMatriz(Grafo graph, int n) {
        System.out.println("print matriz:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph.getMatrizAdjacencia()[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}

