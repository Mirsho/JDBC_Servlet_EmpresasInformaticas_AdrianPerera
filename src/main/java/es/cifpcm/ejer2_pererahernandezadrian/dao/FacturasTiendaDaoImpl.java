/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.dao;

import es.cifpcm.ejer2_pererahernandezadrian.model.Factura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrián Perera en CIFPCM
 */
public class FacturasTiendaDaoImpl implements FacturasTiendaDao {

    @Override
    public List<Factura> getFacturasTiendaDao(Integer idTienda) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            //conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn = DaoFactory.getInstance().getConnection();

            //STEP 4: Execute a query
            String sql = "SELECT * FROM factura WHERE IdTienda=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTienda);
            ResultSet rs = pstmt.executeQuery();
            //STEP 5: Extract data from result set
            listaFacturas.clear();
            while (rs.next()) {
                Factura factura = new Factura();
                //Retrieve by column name
                int nFactura = rs.getInt("nFactura");
                Date fecha = rs.getDate("fecha");
                String cliente = rs.getString("cliente");

                //Lo guardamos en un objeto factura
                factura.setnFactura(nFactura);
                factura.setFecha(fecha);
                factura.setCliente(cliente);
                System.out.println("Objeto factura (para comprobar si se duplica)");
                System.out.println(factura);
                listaFacturas.add(factura);
            }

            //STEP 6: Clean-up environment
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return listaFacturas;
    }
    private final List<Factura> listaFacturas = new ArrayList<>();

    //Logger logger = LoggerFactory.getLogger(TiendaDaoImpl.class);
    private final DaoFactory daoFactory;

    public FacturasTiendaDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        // TODO Auto-generated constructor stub
    }
}
