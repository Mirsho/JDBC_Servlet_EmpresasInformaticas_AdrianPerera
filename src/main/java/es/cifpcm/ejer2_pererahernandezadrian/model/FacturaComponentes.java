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
public class FacturaComponentes {

    private Integer id;
    private String referencia;
    private Integer cantidad;
    private float precioAplicado;

    public Integer getId() {
        return id;
    }

    public String getReferencia() {
        return referencia;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public float getPrecioAplicado() {
        return precioAplicado;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioAplicado(float precioAplicado) {
        this.precioAplicado = precioAplicado;
    }
}
