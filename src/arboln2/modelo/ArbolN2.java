/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln2.modelo;

import arbolbinario.modelo.excepciones.ArbolNException;
import java.io.Serializable;

/**
 *
 * @author seba3
 */
public class ArbolN2 implements Serializable{
    private NodoN2 raiz;

    public NodoN2 getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoN2 raiz) {
        this.raiz = raiz;
    }
    
    //Adicionar
    /*
    Entradas Crear un Nodo
        Dato(Cliente)
        Identificador del Referido (Nodo padre)
        Saber a partir de donde se va a ingresar
    buscarPadre(identificacionPadre, raiz): NodoN2
    buscarNodo(identificacion, raiz): NodoN2
    Si hay datos
        Recorrer todo el arbol hasta que encuentre el padre
            Llamar a un ayudante (temp)
            Si en el que esta parado es el padre
                Se le agrega el hijo (Datos del que igreso)
            Si no
                Pregunto si tiene hijos
                Si tiene hijos
                    Hago ciclo (Recorrido en anchura)
                        Parado en cada hijo llama al adicionar
                            Cambiar la referencia
    Si no hay datos
        El que ingresa es a raiz
    */
    
    public void insertarNodo(Cliente dato, String identificacionPadre, NodoN2 temp) throws ArbolNException{
        /*
        Si hay datos
        Revisar si el cliente ya existe
        Encontrar al padre
        Si el padre existe adicionar
        */
        if(raiz == null){
            raiz = new NodoN2(dato);
        }
        else{
            if(buscarNodo(dato.getIdentificacion(), raiz) == null){
                NodoN2 padreEncontrado = buscarNodo(identificacionPadre, raiz);
                if(padreEncontrado != null){
                    padreEncontrado.getHijos().add(new NodoN2(dato));
                }
                else{
                    throw new ArbolNException("El padre con identificacion " + identificacionPadre + "no existe");
                }
            }
            else{
                throw new ArbolNException("El cliente con identificacion " + dato.getIdentificacion() + "ya existe");
            }
        }
    }
    
    public NodoN2 buscarNodo(String identificacion, NodoN2 temp){
        if(temp.getDato().getIdentificacion() == identificacion){
            return temp;            
        }
        else{
            for(NodoN2 hijo : temp.getHijos()){
                NodoN2 nodoEncontrado = buscarNodo(identificacion, hijo);
                if(nodoEncontrado != null){
                    return nodoEncontrado;
                }
            }
        }
        return null; 
    }
}
