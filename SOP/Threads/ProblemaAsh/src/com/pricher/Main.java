/**
 * Created by Jeferson Machado on 17/08/2016.
 */

package com.pricher;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Diretorio contendo os registros para serem analisados
        File pasta = new File("registros");
        //Balanco para exibicao
        double balanco = 0;
        //Total de vendas
        int totV       = 0;
        //Total de Compras
        int totC       = 0;


        //Verifica se a pasta existe
        ///////////////////////////////////////////////////
        //Inicia o tempo para o calculo de execução das threads
        long inicioT = System.currentTimeMillis();
        //////////////////////////////////////////////////

        //Verifica se a pasta informada existe
        if(pasta.exists()){

            //Verifica se existe arquivos, se não (<= 0)
            if(pasta.listFiles().length <= 0){
                System.err.println("Não existe arquivos para analise");
                System.exit(0);
            }else {

                //Lista todos os arquivos da pasta e joga no vetor File[]
                File[] arquivos = pasta.listFiles();
                AnalisaArquivo[] analise = new AnalisaArquivo[arquivos.length];
                Thread[] threads = new Thread[arquivos.length];

                for (int i = 0; i < arquivos.length; i++) {
                    analise[i] = new AnalisaArquivo(arquivos[i]);
                    threads[i] = new Thread(analise[i]);
                }

                //Inicializa todas as threads
                for (int i = 0; i < arquivos.length; i++) {
                    threads[i].start();
                }

                //Espera pelo resultado de todas as threads
                for (int i = 0; i < arquivos.length; i++) {
                    try {
                        threads[i].join();
                    } catch (InterruptedException e) {
                        System.err.println("Erro na threads "+ threads[i].getName() + "\n");
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < arquivos.length; i++) {
                    balanco += analise[i].getBalanco();
                    totC    += analise[i].getTotC();
                    totV    += analise[i].getTotV();
                }
                //////////////////////////////////////////////////
                //Calcula o tempo de execução das threads
                long fimT = System.currentTimeMillis() - inicioT;
                /////////////////////////////////////////////////


                //Exibe informações ao usuario
                System.out.printf("####### BALANÇO DE VENDAS E COMPRAS [%d milisegundos] #######\n", fimT);
                System.out.println("##");
                System.out.printf("## BALANÇO FINAL: R$:%.2f", balanco);
                System.out.println("##");
                System.out.println("## TOTALIZANDO:");
                System.out.printf("## %d Vendas [RECEITA]\n", totV);
                System.out.printf("## %d Compras [DESPESA]\n", totC);

            }

        }else{
            System.err.printf("Local informado ["+pasta+"] não existe");
        }
    }
}
