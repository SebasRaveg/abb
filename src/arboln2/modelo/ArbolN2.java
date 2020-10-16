/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln2.modelo;

import arbolbinario.modelo.excepciones.ArbolNException;

/**
 *
 * @author seba3
 */
public class ArbolN2 {
    private NodoN2 raiz;
    
    public ArbolN2(){
        
    }

    public NodoN2 getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoN2 raiz) {
        this.raiz = raiz;
    }
    
    public void adicionarNodo(NodoN2 pivote, Dato hijo, Dato padre){
        if(raiz==null)
        {
            raiz= new NodoN2(hijo);
        }    
        else
        {
            if(pivote.getDato().getIdentificacion().equals(padre.getIdentificacion()))
            {
                pivote.aumentarHijo(new NodoN2(hijo));
            }
            else
            {
                for(NodoN2 hijoPivote: pivote.getHijos())
                {
                    if(hijoPivote.getDato().getIdentificacion().equals(padre.getIdentificacion()))
                    {
                        hijoPivote.aumentarHijo(new NodoN2(hijo));
                        break;
                    }
                    else
                    {
                        adicionarNodo(hijoPivote, hijo, padre);
                    }    
                }    
            }
        }    
    }
    
    public String recorrerArbolN2() throws ArbolNException{
        if(raiz!=null)
        {
            String listado="";
            listado += recorrerArbolN2(raiz, listado);
            return listado;
        }   
        throw new ArbolNException("El árbol está vacío");
    }
    
    public String recorrerArbolN2(NodoN2 pivote, String listado)
    {
        listado += "\n"+pivote.getDato();
        for(NodoN2 hijo: pivote.getHijos())
        {
            listado = recorrerArbolN2(hijo, listado);
        }   
        return listado;
    }
    
}
