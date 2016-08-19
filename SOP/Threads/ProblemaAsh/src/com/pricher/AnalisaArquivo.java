package com.pricher;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Jeferson Machado on 17/08/2016.
 */
public class AnalisaArquivo implements Runnable{

        private Scanner ler;
        private double balanco;
        private int totV;
        private int totC;


        public AnalisaArquivo(File arq){
            //Inicializa as variaveis contadoras
            totV = 0;
            totC = 0;

            //Atribui o arquivo(arq) recebido por parametro
            try {
                ler = new Scanner(arq);
            } catch (FileNotFoundException e) {
                System.err.println(arq.getName() + "não localizado");
                e.printStackTrace();
            }
        }


    @Override
    public void run() {
            String[] linha;

            while(ler.hasNext()){
                linha = ler.next().split(":");

                if(linha[0].equals("v") || linha[0].equals("V")){

                    balanco += Double.parseDouble(linha[1]);
                    totV++;

                }else if(linha[0].equals("c") || linha[0].equals("C")){

                    balanco -= Double.parseDouble(linha[1]);
                    totC++;
                }else{
                    System.err.println("Erro na linha");
                }
            }

    }



    /**
     * <b>getBalanco</b>
     * Retorna o balanço ou seja o resultado das adições e subtrações
     * @return double
     */
    public double getBalanco(){
        return balanco;
    }

    /**
     * <b>getTotC</b>
     * Retorna o total de vendas
     * @return int
     */
    public int getTotV(){return totV;}

    /**
     * <b>getTotC</b>
     * Retorna o total de compras
     * @return int
     */
    public int getTotC(){return totC;}

}
