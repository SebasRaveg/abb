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
    
    //preOrden
    
    public ArrayList preOrden() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        if (raiz != null) {
            preOrden(raiz, l);
        }        
        return l;
    }

    private void preOrden(Nodo temp, ArrayList l)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            l.add(temp.getDato());
            preOrden(temp.getIzquierda(), l);
            preOrden(temp.getDerecha(), l);
        }
    }
    
     //inOrden
    
    public ArrayList inOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }
    
    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(),l);
        }
    }
    
    //posOrden
    
    public ArrayList posOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        posOrden(raiz,l);
        return l;
    }
    
    private void posOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            posOrden(reco.getIzquierda(),l);
            posOrden(reco.getDerecha(),l);
            l.add(reco.getDato() + " ");
        }
    }
    
    // PorNiveles
    
    public ArrayList impNiveles() throws ArbolBinarioException{
        isLleno();
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
        
    //Hojas
    
    public ArrayList getHojas() throws ArbolBinarioException {
        isLleno();
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }
    
    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzquierda(), l);
            getHojas(r.getDerecha(), l);
        }

    }
       
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzquierda()== null && x.getDerecha()== null);
    }
    
    //Podar
    public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    //Balance
    
    int subizq = 0;
    int subder = 0;

    public String imprimirBalance() {
         subizq = 0;
         subder = 0;

        Balance(this.raiz, true, 0);
        //System.out.println("lado Izquierdo " + subizq + " Lado Derecho " + subder);
        if (subizq - subder == 0) {
            return ("El balance es: 0 ");
        } else if (subizq - subder == -1) {
            return("El balance es -1, derecha");
        } else if (subizq - subder == 1) {
            return("El balance 1, izquierda");
        } else {
            return("No es balanceado.."
                    + "porque es mas grande el lado "
                    + ((subizq > subder) ? "Izquierdo" : "Derecho"));
        }

    }

    public void Balance(Nodo reco, boolean lado, int i) {
        if (reco != null) {
            if (reco.getDerecha()== null && reco.getIzquierda()== null) {
                if (lado) {
                    subder = (i > subder) ? i : subder;
                } else {
                    subizq = (i > subizq) ? i : subizq;
                }
            }
            Balance(reco.getDerecha(), lado, i + 1);
            if (i == 0) {
                lado = false;
            }
            Balance(reco.getIzquierda(), lado, i + 1);
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
