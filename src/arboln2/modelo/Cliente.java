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
public class Cliente implements Serializable{
    private String identificacion;
    private String nombre;
    private String apellido;
    private Ciudad ciudad;
    private String referidoDe;
    private String referidoN; 
    
    public Cliente(){
        
    }
    
    public Cliente(String identificacion, String nombre, String apellido, Ciudad ciudad, String referidoDe, String referidoN) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.referidoDe = referidoDe;
        this.referidoN = referidoN;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getReferidoDe() {
        return referidoDe;
    }

    public void setReferidoDe(String referidoDe) {
        this.referidoDe = referidoDe;
    }

    public String getReferidoN() {
        return referidoN;
    }

    public void setReferidoN(String referidoN) {
        this.referidoN = referidoN;
    }   
}
