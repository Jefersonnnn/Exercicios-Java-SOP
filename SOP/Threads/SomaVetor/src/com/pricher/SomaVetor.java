package com.pricher;

/**
 * Created by CASA on 13/08/2016.
 */
public class SomaVetor extends Thread {


    private int[] VetorA;
    private int[] VetorB;
    private int[] VetorC;
    private int valores;
    private int ini;
    private int fim;

    public SomaVetor(int[] vetorA, int[] vetorB, int n, int inicio, int fim){
        this.valores = n;
        this.ini     = inicio;
        this.fim     = fim;

        this.VetorA = new int[n];
        this.VetorB = new int[n];
        this.VetorC = new int[fim - inicio + 1];

        this.VetorA = vetorA;
        this.VetorB = vetorB;
    }


    @Override
    public void run() {
        //Lembrete: VC[0] = VA[0] + VB[0], ..., VC[n] = VA[n] + VB[n */
        int j = 0;
        for (int i = ini; i <= fim ; i++){
                if(j < (fim - ini + 1)) {
                    VetorC[j] = VetorA[i] + VetorB[i];
                    j++;
                }



        }
    }

    public int[] getVetorC(){
        return VetorC;
    }
}
