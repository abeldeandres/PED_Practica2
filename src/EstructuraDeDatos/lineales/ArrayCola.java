/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructuraDeDatos.lineales;

import EstructuraDeDatos.modelos.TADCola;

/**
 *
 * @author abelillo
 */
public class ArrayCola<E> implements TADCola<E> {
    protected E elArray[];
    protected int fin,primero,tallaActual;
    protected static final int CAPACIDAD_POR_DEFECTO=200;
    
    public ArrayCola(){
        elArray=(E[]) new Object[CAPACIDAD_POR_DEFECTO];
        tallaActual=0;
        primero=0;
        fin=-1;
    }
    
    @Override
    public void encolar(E elemento) {
        if(tallaActual == elArray.length) duplicarArray();
        fin = incrementa(fin);
        elArray[fin]=elemento;
        tallaActual++;
    }

    @Override
    public E desencolar() {
        E elPrimero=elArray[primero];
        primero=incrementa(primero);
        tallaActual--;
        return elPrimero;
    }

    @Override
    public E primero() {
        return elArray[primero];
    }

    @Override
    public boolean esVacia() {
        return (tallaActual==0);
    }
   /* 
    public String toString(){
        String res="";
        int aux = primero;
        for (int i =0; i<tallaActual; i++, aux= incrementa(aux))
            res+=elArray[aux]+"\n";
        return res;
    }*/
    
    public String toString() {
        String res = "";
        int aux = fin;
        for ( int i = 0; i < tallaActual; i++, aux = decrementa(aux) )

        res += elArray[aux] + "\n";
        return res;
    }
    
    private int incrementa(int indice){
        if(++indice==elArray.length) indice=0;
        return indice;
    }
    
    private int decrementa(int indice){
        if(--indice==elArray.length) indice=0;
        return indice;
    }
    
    
    private void duplicarArray(){
        E nuevo[]=(E[]) new Object[elArray.length*2];
        for (int i=0; i<tallaActual;i++,primero=incrementa(primero))
            nuevo[i]=elArray[primero];
        elArray=nuevo;
        primero=0;
        fin=tallaActual-1;
    }   
   
    
    
}
