package arvoregeradoraderotulosminimos;

/**
*
* @author luisfernando
*/

public class MVCA {

	public static int calculaComponentesConexas(Grafo g) {
	       
        int numComponentesConexas = 0;
        int verticesVisitado[] = new int[g.getQuantidadeVertices()];
      
        for (int i = 0; i < g.getQuantidadeVertices(); i++) {
            g.getCorVertices()[i] = -1;
            verticesVisitado[i] = -1;
        }
       
        for (int i = 0; i < g.getQuantidadeVertices(); i++) {
            //Se a cor ainda for "-1", procura componentes conexas, pois ainda 
            //não passou por todas as componentes conexas
            if (g.getCorVertices()[i] == -1) {
            	numComponentesConexas++;
                //System.out.println(numCC);
                //toda vez que volta da busca em largura, é uma nova componenete conexa
                buscaEmProfundidade(g, i, numComponentesConexas, verticesVisitado);
            }
        }
        //retorna o numero de componentes conexas
        return numComponentesConexas;

    }

    public static void buscaEmProfundidade(Grafo g, int i, int numCC, int verticesVisitado[]) {

        //inicializa a componente conexa
        verticesVisitado[i] = 1;
        g.getCorVertices()[i] = numCC;
        
        for (int j = 0; j < g.getQuantidadeVertices(); j++) {
//        	System.out.println("if((" + g.getComponenteConexa()[j] + " == -1) && ("+ g.getMatrizAdjacencia()[i][j] +")   != -1)");
        
            //if ((g.getMatrizAdjacencia()[i][j] != -1) && (g.getComponenteConexa()[j] == -1)) {
            if((g.getCorVertices()[j] == -1) && (g.getMatrizAdjacencia()[i][j]) != -1){
                //System.out.println("Opa entrou no if");
                buscaEmProfundidade(g, j, numCC, verticesVisitado);
            }
        }

    }
}
