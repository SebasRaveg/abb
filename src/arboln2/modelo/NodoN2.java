/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln2.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author seba3
 */
public class NodoN2 {
    private Cliente dato;
    private List<NodoN2> hijos;
    
    public NodoN2(Cliente dato){
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }
    
    public Cliente getDato() {
        return dato;
    }

    public void setDato(Cliente dato) {
        this.dato = dato;
    }

    public List<NodoN2> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoN2> hijos) {
        this.hijos = hijos;
    }
}
