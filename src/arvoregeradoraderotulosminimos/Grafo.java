package arvoregeradoraderotulosminimos;

/**
*
* @author luisfernando
*/
public class Grafo {

	 private Integer matrizAdjacencia[][];
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

}
