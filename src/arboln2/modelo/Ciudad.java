/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln2.modelo;

import java.io.Serializable;

/**
 *
 * @author seba3
 */
public class Ciudad implements Serializable{
    private short codigo;
    private String nombreCiudad;
    
    public Ciudad(){
        
    }
    
    public Ciudad(short codigo, String nombreCiudad){
        this.codigo = codigo;
        this.nombreCiudad = nombreCiudad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    
    @Override
    public String toString() {
        return "Ciudad{" + "codigo=" + codigo + ", nombreCiudad=" + nombreCiudad + '}';
    }
    
}
