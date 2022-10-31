/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.model;

/**
 *
 * @author Adrián Perera en CIFPCM
 */
public class Tienda {

    private Integer idTienda;
    private String nombreTienda;
    private String direccion;
    private String localidad;
    private String telefono;

    public Tienda(){
        idTienda = null;
        nombreTienda = null;
    }
    
    public Tienda(Integer idTienda, String nombreTienda, String direccion, String localidad, String telefono) {
        this.idTienda = idTienda;
        this.nombreTienda = nombreTienda;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public Integer getIdTienda() {
        return idTienda;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
