/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {

    private Nodo raiz;

    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en preorden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
    */
    public ArrayList preOrden() throws ArbolBinarioException {
        //isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            preOrden(raiz, l);
        }        
        return l;
    }
    /**
     * Método recursivo que recorre todo el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado Acumulador para registrar el dato del nodo visitado
    */
    private void preOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            listado.add(temp.getDato());
            preOrden(temp.getIzquierda(), listado);
            preOrden(temp.getDerecha(), listado);
        }
    }
    
    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en inOrden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
    */
    public ArrayList inOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }
    
    /**
     * Método recursivo que recorre todo el árbol en inOrden
     * @param reco Ayudante que toma referencia en un nodo
     * @param l Acumulador para registrar el dato del nodo visitado
    */
    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(),l);
        }
    }
    
    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el
     * árbol en posOrden
     *
     * @return ArrayList
     * @throws ArbolBinarioException
    */
    public ArrayList posOrden() throws ArbolBinarioException{
        //isLleno();
        ArrayList l=new ArrayList();
        posOrden(raiz,l);
        return l;
    }
    
    /**
     * Método recursivo que recorre todo el árbol en posOrden
     * @param reco Ayudante que toma referencia en un nodo
     * @param l Acumulador para registrar el dato del nodo visitado
    */
    private void posOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            posOrden(reco.getIzquierda(),l);
            posOrden(reco.getDerecha(),l);
            l.add(reco.getDato() + " ");
        }
    }
    
    public ArrayList impNiveles() throws ArbolBinarioException{
        //isLleno();
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }
    
    private void impNiveles(Nodo reco, int nivel, ArrayList l){
        if (reco != null) {
            impNiveles(reco.getIzquierda(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDerecha(), nivel + 1, l);
        }
    }
    
    
    public void llenarArbol(String datos) throws ArbolBinarioException
    {
        String[] arrayDatos= datos.split(",");
        for(String cadena: arrayDatos)
        {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }
        
    }

}
