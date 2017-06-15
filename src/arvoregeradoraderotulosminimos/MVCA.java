package arvoregeradoraderotulosminimos;

import java.util.ArrayList;
/**
*
* @author luisfernando
*/

public class MVCA {
	
    public static ArrayList<Integer> MVCA(Grafo graph){
    	//Conjunto contendo os rótulos já utilizados, e que conectam o maior numero de componentes.
    	ArrayList<Integer> rotulosUtilizados = new ArrayList<Integer>();
        
        //Rotulos utilizados em cada iteracao que procura o rotulo com maior numero de componentes conexas;
        ArrayList<Integer> rotulosUtilizadosNaIteracao = new ArrayList<Integer>();
        
        //Criando o subgrafo de graph.
        Grafo subgrafo = new Grafo();
        Grafo h = new Grafo(graph.getQuantidadeVertices());
        
        //Númedo de componentes conexas do Grafo H
        //Inicialmente a quantidade de CC é a quantidade de vertices do grafo
        int quantidadeCC = graph.getQuantidadeVertices();

      //Quantidade de CC do subgrafo para cada iteração, em busca dos rotulos minimos
        int quantCCSubgrafo;
        
        //Menor label da iteracao
        int menorLabel = -1;
        int aux = 0;
        
        //iterar enquanto existir mais de uma componente conexa, ou seja, enquanto H não ser apenas uma Componente Conexa
       do{
            rotulosUtilizadosNaIteracao = new ArrayList<Integer>(rotulosUtilizados); 
            
            for (int i = 0; i < graph.getQuantidadeDeRotulos(); i++) {
                //System.out.println("i: " + i);
            	
            	//seleciona um rotulo nao usado c (pertencente a L-C) que minimiza Comp(C U {c})
            	//Pega apenas os rotulos que ainda nao foram usados na iteração, de 0 a QtdRotulos.
                if (!rotulosUtilizadosNaIteracao.contains(i)){
                    //System.out.println("Vezes laço do: " + aux);                    
                    
                    //Gera e adiciona subgrafo com o rotulo i;
                    Grafo sub = geraSubgrafo(graph, h, i);
                    
                    //Calcula quantidade de componentes conexas do subgrafo
                    quantCCSubgrafo = calculaComponentesConexas(sub);

                    if (quantCCSubgrafo<quantidadeCC){
                        menorLabel = i;
                       
                        quantidadeCC = quantCCSubgrafo;
                        subgrafo = sub;
                    }
                    rotulosUtilizadosNaIteracao.add(i);
                }
                
            }
            //Caso o grafo nao seja conexo. Significa que esta em loop, o rotulo continua o mesmo da iteracao passada.
            if (rotulosUtilizados.contains(menorLabel)){
            	System.out.println("Grafo não conexo");
            	return null;
            }
            	
            aux++;
            rotulosUtilizados.add(menorLabel);
            h = subgrafo;
            
       }while(quantidadeCC>1);
        
//        LerArquivo.printMatriz(h, h.getQuantidadeVertices());
        return rotulosUtilizados;
    }

	public static int calculaComponentesConexas(Grafo g) {
	       
        int numComponentesConexas = 0;
        
        for (int i = 0; i < g.getQuantidadeVertices(); i++) {
            //Se a cor ainda for "-1", procura componentes conexas, pois ainda 
            //não passou por todas as componentes conexas
            if (g.getCorVertices()[i] == -1) {
            	numComponentesConexas++;
                //System.out.println(numCC);
                //toda vez que volta da busca em largura, é uma nova componenete conexa
                buscaEmProfundidade(g, i, numComponentesConexas);
            }
        }
        //retorna o numero de componentes conexas
        return numComponentesConexas;

    }

    public static void buscaEmProfundidade(Grafo g, int i, int numCC) {

        //inicializa a componente conexa
        g.getCorVertices()[i] = numCC;
        
        for (int j = 0; j < g.getQuantidadeVertices(); j++) {
//        	System.out.println("if((" + g.getComponenteConexa()[j] + " == -1) && ("+ g.getMatrizAdjacencia()[i][j] +")   != -1)");
        
            //if ((g.getMatrizAdjacencia()[i][j] != -1) && (g.getComponenteConexa()[j] == -1)) {
            if((g.getCorVertices()[j] == -1) && (g.getMatrizAdjacencia()[i][j] != -1) && (g.getMatrizAdjacencia()[i][j]) != 2){
                buscaEmProfundidade(g, j, numCC);
            }
        }
    }
       
    public static Grafo geraSubgrafo(Grafo graph, Grafo h, int rotulo) {

        Grafo g = new Grafo(h.getQuantidadeVertices());

        //copia as arestas de "h" para "g"
        for (int i = 0; i < h.getQuantidadeVertices(); i++) {
            System.arraycopy(h.getMatrizAdjacencia()[i], 0, g.getMatrizAdjacencia()[i], 0, h.getQuantidadeVertices());
        }

        //insere as novas arestas com o rotulo passado
        for (int i = 0; i < h.getQuantidadeVertices(); i++) {
            for (int j = 0; j < h.getQuantidadeVertices(); j++) {
                if (graph.getMatrizAdjacencia()[i][j] == rotulo) {
                    g.getMatrizAdjacencia()[i][j] = graph.getMatrizAdjacencia()[i][j];
                }
            }
        }
        return g;

    }
}
