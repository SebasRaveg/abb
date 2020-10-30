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
    private String ciudad;
    private String referidoDe;
    private String referidoN;
    
    public Cliente(){
        
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
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
