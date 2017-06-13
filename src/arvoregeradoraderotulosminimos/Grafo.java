package arvoregeradoraderotulosminimos;

import aps.ImportaMatriz;

/**
*
* @author luisfernando
*/
public class Grafo {
	
	public Grafo(int tamanho) {
        super();
        this.matrizAdjacencia = inicializaMatriz(tamanho);
        this.setQuantidadeVertices(tamanho);
        this.setCorVertices(LerArquivo.inicializaCorVertices(tamanho));

    }

    public Grafo() {
        super();
    }


	 	private Integer[][] matrizAdjacencia;
	    private int qtdVertices;
	    private int qtdRotulos;
	    private int[] corVertices;

		public Integer[][] getMatrizAdjacencia() {
	        return matrizAdjacencia;
	    }

	    public void setMatrizAdjacencia(Integer[][] matrizAdjacencia) {
	        this.matrizAdjacencia = matrizAdjacencia;
	    }
	    
	    public int[] getCorVertices() {
	        return corVertices;
	    }
	  
	    public void setCorVertices(int[] cores) {
	        this.corVertices = cores;
	    }
	    
	    public int getQuantidadeDeRotulos() {
	        return qtdRotulos;
	    }

	    public void setQuantidadeDeRotulos(int rotulos) {
	        this.qtdRotulos = rotulos;
	    }
	    
	    public int getQuantidadeVertices() {
	        return qtdVertices;
	    }

	    public void setQuantidadeVertices(int vertices) {
	        this.qtdVertices = vertices;
	    }

	    public Integer[][] inicializaMatriz(int tamanho) {
	        Integer matriz[][] = new Integer[tamanho][tamanho];
	        for (int i = 0; i < tamanho; i++) {
	            for (int j = 0; j < tamanho; j++) {
	                matriz[i][j] = -1;
	            }
	        }
	        return matriz;
	    }
}
