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

//    public File LerArquivo(String caminho) {
//        File arq = new File(caminho);
//        if (arq.isFile()) {
//            return arq;
//        }
//        return null;
//    }
//
//    String lerArquivo(String caminho) {
//        try {
//            List<String> linhas = new ArrayList<String>();
//            File arq = new File(caminho);
//            BufferedReader bfr = null;
//            bfr = new BufferedReader(new FileReader(arq));
//            try {
//                while (bfr.readLine() != null) {
//                    String teste = bfr.readLine();
//                    System.out.println(teste);
//                }
//
//            } catch (IOException ex) {
//                Logger.getLogger(LerArquivo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(LerArquivo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return " ";
//    }
	public static ArrayList <Integer[][]> leArquivo(String caminho) throws IOException {
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

        int indice = vertices;
        System.out.println("vertices: " + indice);
        
        ArrayList <Integer[][]> Grafos = new ArrayList<>();
        Integer[][] matrizAdjacencia = new Integer[vertices][vertices];
               
        int i = 0;
        int aux = 0;
        int pos = 0;

        //Enquanto não houver mais o que ser lido, continuar
        while (((linha = br.readLine()) != null)) {
            //Caso um grafo inteiro já foi lido
            if (i % vertices == 0) {
                //Caso não seja a primeira execução, para não gerar um grafo totalmente nulo
                if (aux != 0) {
            		Grafos.add(matrizAdjacencia);
            		matrizAdjacencia = new Integer[vertices][vertices];
            	}
            	i = 0;
            }
            s = linha.split(" ");
            
            //Preenchendo a matriz. Matriz[ultimaLinha até a primeira][Primeiracoluna até a ultima]
            for (int j = 0; j < s.length; j++) {
                if (s[j].equals("")) {
                	matrizAdjacencia[indice - i - 1][j] = rotulos;
                }
                else{
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
        Grafos.add(matrizAdjacencia);
        
        br.close();
        fr.close();

        //Imprimir Array de grafos pra testar
        for (Integer[][] grafo : Grafos){
        	printMatriz(grafo, vertices);
        }
        
        return Grafos;
    }
   
//  public static Integer[][] inicializaMatriz(int tamanho) {
//        Integer matriz[][] = new Integer[tamanho][tamanho];
//        for (int i = 0; i < tamanho; i++) {
//            for (int j = 0; j < tamanho; j++) {
//                matriz[i][j] = -1;
//            }
//        }
//        return matriz;
//    }
  
  public static void printMatriz(Integer[][] matriz, int n) {
        System.out.println("print matriz:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}


