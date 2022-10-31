/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.model;

import java.sql.Date;

/**
 *
 * @author Adrián Perera en CIFPCM
 */
public class Factura {

    private Integer nFactura;
    private Date fecha;
    private String cliente;

    public Integer getnFactura() {
        return nFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setnFactura(Integer nFactura) {
        this.nFactura = nFactura;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
