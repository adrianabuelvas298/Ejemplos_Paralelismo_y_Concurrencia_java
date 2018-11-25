/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paralelismo_java;

/**
 *
 * @author Jose David
 */
public class Paralelismo implements Runnable{
     private static int[] v;
     private int p, q,n;
     
      private Paralelismo(int[] v,int p, int q){
        this.p=p;
        this.q=q;
        this.v=v;
    }     
     private static Thread DVThread(int[] v,int p, int q){
            Paralelismo dv= new Paralelismo(v,p,q);
            Thread dvt= new Thread(dv);
            dvt.start();
            return dvt;
     }
    
     
     static int[] ordenarVector(int p, int q, int[] v){
     Thread qst= DVThread(v,p,q);
     try {
     qst.join();
     } catch (InterruptedException e) {;}
     
     final int N = Math.abs(p - q) + 1;
     int[] result = new int[N];
    
    // Caso Base
    
    if (Math.abs(p - q) == 1) {
      if (v[p] > v[q]) {
        result[0] = v[q];
        result[1] = v[p];
      } else {
        result[0] = v[p];
        result[1] = v[q];
      }
      
      
      
      
      
    } else { // Subproblemas
        
    
      
      int[] izq = ordenarVector(p, (p + q) / 2, v);
      int[] der = ordenarVector(((p + q) / 2) + 1, q, v);
      result = combinar(izq, der, N);
    }
    return (result);
  }
  

  
  static int[] combinar(int[] izq, int[] der, final int N) {
    int i = 0;
    int j = 0;
    int k = 0;
    int[] result = new int[N]; 
    while ((i != izq.length) && (j != der.length)) {
      if (izq[i] < der[j]) {
        result[k] = izq[i];
        i++;
      } else {
        result[k] = der[j];
        j++;
      }
      k++;
    }
    if (i != izq.length) {
      while (i != izq.length) {
        result[k] = izq[i];
        k++;
        i++;
      }
    } else {
      while (j != der.length) {
        result[k] = der[j];
        k++;
        j++;
      }
    }
    return (result);
  }

  public static void main(String[] args) {
    int[] vector = {35, 10, 1, 2, 7, 3, 8, 4};
    vector = ordenarVector(0, vector.length - 1, vector);
    System.out.println("USANDO EL PARADIGMA QUICKSORT PARALELO RECURSIVO \n");
    for (int i = 0; i != vector.length; i++)
      System.out.print(vector[i] + " ");
    System.out.println();
  }

    @Override
    public void run() {
        
    }
 
}
