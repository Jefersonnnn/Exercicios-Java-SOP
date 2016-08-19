/*Problema 1. Elabore um programa multithread
que recebe dez valores inteiros para um vetor VA e
outros dez valores inteiros para um vetor VB (para receber os valores utilize apenas a thread principal).
O programa deve fazer a soma destes dois vetores utilizando quatro threads gerando um
vetor resultante Vc. Depois reescreva o seu programa para receber mil valores por vetor ao invés de
apenas dez.

Lembrete: VC[0] = VA[0] + VB[0], ..., VC[n] = VA[n] + VB[n */

package com.pricher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc;
        int valores;
        int totThreads = 4;

        sc = new Scanner(System.in);
        System.out.println("Digite a quantidade de valores para o vetor: ");
        valores = sc.nextInt();

        int[] vetorA = new int[valores];
        int[] vetorB = new int[valores];
        int[] vetorC = new int[valores];
        //int[][] iniFim = new int[4][2];

        int iniT1 = 0, fimT1 = 0;
        int iniT2 = 0, fimT2 = 0;
        int iniT3 = 0, fimT3 = 0;
        int iniT4 = 0, fimT4 = 0;



        System.out.println("Digite [" + valores + "] valores para o vetor A: ");
        for(int i = 0; i < valores; i++){
            vetorA[i] = sc.nextInt();
        }

        System.out.println("Digite [" + valores + "] valores para o vetor B: ");
        for(int i = 0; i < valores; i++){
            vetorB[i] = sc.nextInt();
        }



        int div = (int)(valores / totThreads);
        int inicio = 0;
        int resto = 0;
        int j = 0;

        for(int i = 0; i < valores; i++){

            if(valores - div == i && (valores % totThreads) != 0){

                resto = valores % totThreads;
                fimT4 += resto;
                break;

            }

            if((i + 1) % div  == 0){
                if(j == 0) {
                    iniT1 = inicio;
                    fimT1 = i;

                }else if(j == 1){
                    iniT2 = inicio;
                    fimT2 = i;

                }else if(j == 2){
                    iniT3 = inicio;
                    fimT3 = i;

                }else{
                    iniT4 = inicio;
                    fimT4 = i;

                }
                inicio = i + 1;
                j++;
            }
        }

        /////////////////////////////////////////////////////////////////
        double inicioT = System.currentTimeMillis();

        SomaVetor T1 = new SomaVetor(vetorA, vetorB, valores, iniT1, fimT1);
        SomaVetor T2 = new SomaVetor(vetorA, vetorB, valores, iniT2, fimT2);
        SomaVetor T3 = new SomaVetor(vetorA, vetorB, valores, iniT3, fimT3);
        SomaVetor T4 = new SomaVetor(vetorA, vetorB, valores, iniT4, fimT4);

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        try {
            T1.join();
            T2.join();
            T3.join();
            T4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /////////////////////////////////////////////////////////////////

        vetorC = uneVetores(uneVetores(T1.getVetorC(), T2.getVetorC()), uneVetores(T3.getVetorC(), T4.getVetorC()));
        double tempo = System.currentTimeMillis() - inicioT;


        System.out.println("Resposta em " + tempo + "milisegundos");
        for(int i : vetorC){
          System.out.print(i+" ") ;
        }
    }

    public static int[] uneVetores(int v1[], int v2[]){

        int[] vetoresUnidos = new int[v1.length + v2.length];
        int ondeParou = 0;

        for (int i = 0; i < v1.length; i++){
            vetoresUnidos[ondeParou] = v1[i];
            ondeParou = i+1;
        }

        int j = ondeParou;
        for(int i = 0; i < v2.length; i++){
            vetoresUnidos[j] = v2[i];
            j++;
        }

        return vetoresUnidos;
    }

}
