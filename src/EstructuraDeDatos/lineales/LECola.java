/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructuraDeDatos.lineales;

import EstructuraDeDatos.modelos.TADCola;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public class LECola<E> implements TADCola<E>{
    private Nodo<E> primero;
    private Nodo<E> ultimo;
    private int talla=0;
    
    
    public class Nodo<E>{
        E dato;
        Nodo siguiente;
    }
    
    public void encolar(E elemento)
    {
        Nodo<E> nuevo = new Nodo<E>();
        nuevo.dato=elemento;
        talla++;
        if(primero==null)
        {
            primero=nuevo;
            ultimo=nuevo;
            
        }
        else
        {
            nuevo.siguiente=ultimo;
            ultimo=nuevo;
        }
    }
    
    public E desencolar()
    {
        E dato;
        Nodo<E> aux= new Nodo<E>();
        aux=ultimo;
        if(aux.siguiente==null)
        {
            dato=aux.dato;
            primero=null;
            return dato;
        }
        else{
            while(aux.siguiente!=primero)
            {
                aux=aux.siguiente;
            }
            dato=primero.dato;
            primero=aux;
            aux.siguiente=null;
            return dato;
        }
       
    }
    
    public E primero()
    {
        return primero.dato;
    }
    
    public boolean esVacia()
    {
        if (primero==null)return true;
        else return false;
    }
     
    public String toString()
    {
        String res="";
        Nodo<E> aux= new Nodo<E>();
        for(aux=ultimo; aux!=null; aux=aux.siguiente)
        {
            res+=aux.dato.toString()+"\n";
        }
        return res;   
    }
}
