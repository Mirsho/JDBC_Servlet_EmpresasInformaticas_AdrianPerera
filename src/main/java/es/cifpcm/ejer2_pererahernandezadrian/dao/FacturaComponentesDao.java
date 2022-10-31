/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.dao;

import es.cifpcm.ejer2_pererahernandezadrian.model.FacturaComponentes;
import java.util.List;

/**
 *
 * @author Adrián Perera en CIFPCM
 */
public interface FacturaComponentesDao {
    List<FacturaComponentes> getFacturaComponentesDao(Integer nFactura);
}
