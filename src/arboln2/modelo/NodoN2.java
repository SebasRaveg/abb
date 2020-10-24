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
    private Empleados dato;
    private List<NodoN2> hijos;
    
    public NodoN2(Empleados dato){
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public Empleados getDato() {
        return dato;
    }

    public void setDato(Empleados dato) {
        this.dato = dato;
    }

    public List<NodoN2> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoN2> hijos) {
        this.hijos = hijos;
    }
    
    public int obtenerNumeroHijos(){
        return hijos.size();
    }
            
    public void aumentarHijo(NodoN2 hijo)
    {
        hijos.add(hijo);
    }
}
