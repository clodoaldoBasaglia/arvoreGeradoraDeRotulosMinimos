///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package arvoregeradoraderotulosminimos;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author clodoaldo
// */
//public class ArvoreGeradoraDeRotulosMinimos {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        LerArquivo la = new LerArquivo();
//        String caminho = "/home/clodoaldo/Documentos/APS.Teoria.dos.Grafos.2017.1/instancias/";
//        SondaDeArquivos sdd = new SondaDeArquivos();
//        System.out.println(sdd.sondaDeArquivos(caminho).size());
//        Map<String, ArrayList<String>> sondaDeArquivos = sdd.sondaDeArquivos(caminho);
//        Iterator<Map.Entry<String, ArrayList<String>>> iterator = sondaDeArquivos.entrySet().iterator();
//        Map<String,ArrayList<Integer[][]>>  mapaGrafos = new HashMap<>();
//        while (iterator.hasNext()) {
//            String chave = iterator.next().getKey();
//            ArrayList<String> get = sondaDeArquivos.get(chave);
//            for (String string : get) {
//                try {
//                    ArrayList<Grafo> leArquivo = la.leArquivo(caminho + chave + "/" + string);
//                    mapaGrafos.put(chave+":"+string, leArquivo);
//                } catch (IOException ex) {
//                    Logger.getLogger(ArvoreGeradoraDeRotulosMinimos.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
////        Iterator<Map.Entry<String, ArrayList<Integer[][]>>> iterator1 = mapaGrafos.entrySet().iterator();
////        while(iterator1.hasNext()){
////            System.out.println(iterator1.next().getKey());
////        }
//        System.out.println(mapaGrafos.size());
//
//    } 
//
//}
