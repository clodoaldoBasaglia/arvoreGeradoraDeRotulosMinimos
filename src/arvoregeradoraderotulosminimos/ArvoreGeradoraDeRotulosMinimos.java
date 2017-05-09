/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoregeradoraderotulosminimos;

/**
 *
 * @author clodoaldo
 */
public class ArvoreGeradoraDeRotulosMinimos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String caminho = "/home/clodoaldo/Documentos/APS.Teoria.dos.Grafos.2017.1/instancias/GROUP 1/HDGraph20_20.txt";
        LerArquivo la = new LerArquivo();
        la.lerArquivo(caminho);
    }
    
}
