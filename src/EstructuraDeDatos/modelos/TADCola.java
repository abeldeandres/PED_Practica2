/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstructuraDeDatos.modelos;

/**
 *
 * @author Abel de Andrés e Iván Barbado
 */
public interface TADCola<E> {
    public void encolar(E elemento);
    public E desencolar();
    public E primero();
    public boolean esVacia();
}
