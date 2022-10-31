/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.dao;

import es.cifpcm.ejer2_pererahernandezadrian.model.Tienda;
import java.sql.Connection;
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
public class ListaTiendasDaoImpl implements ListaTiendasDao {

    @Override
    public List<Tienda> getListaTiendasDao() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            //conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn = DaoFactory.getInstance().getConnection();

            //STEP 4: Execute a query
            String sql = "SELECT * FROM tienda";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            //STEP 5: Extract data from result set
            listaTiendas.clear();
            while (rs.next()) {
                Tienda tienda = new Tienda();
                //Retrieve by column name
                int idTienda = rs.getInt("idTienda");
                String nombreTienda = rs.getString("nombreTienda");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String telefono = rs.getString("telefono");
                System.out.println("Nombre Tienda");
                System.out.println(nombreTienda);

                //Lo guardamos en un objeto factura
                tienda.setIdTienda(idTienda);
                tienda.setNombreTienda(nombreTienda);
                tienda.setDireccion(direccion);
                tienda.setLocalidad(localidad);
                tienda.setTelefono(telefono);
                System.out.println("Objeto factura (para comprobar si se duplica)");
                System.out.println(tienda);
                listaTiendas.add(tienda);
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
        return listaTiendas;
    }
    private final List<Tienda> listaTiendas = new ArrayList<>();

    //Logger logger = LoggerFactory.getLogger(TiendaDaoImpl.class);
    private final DaoFactory daoFactory;

    public ListaTiendasDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        // TODO Auto-generated constructor stub
    }
}
