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
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class TiendaDaoImpl implements TiendaDao {

    @Override
    public Tienda getTiendaDao(Integer numeroabuscar) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            //conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn = DaoFactory.getInstance().getConnection();

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql = "SELECT * FROM tienda WHERE IdTienda=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, numeroabuscar);
            ResultSet rs = pstmt.executeQuery();

            tienda = new Tienda();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int idTienda = rs.getInt("idTienda");
                String nombreTienda = rs.getString("nombreTienda");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String telefono = rs.getString("telefono");
                System.out.println("Nombre Tienda");
                System.out.println(nombreTienda);
                //Lo guardamos en el objeto tienda
                tienda.setIdTienda(idTienda);
                tienda.setNombreTienda(nombreTienda);
                tienda.setDireccion(direccion);
                tienda.setLocalidad(localidad);
                tienda.setTelefono(telefono);
            }
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
        return tienda;
    }
    private Tienda tienda;

    //Logger logger = LoggerFactory.getLogger(TiendaDaoImpl.class);
    private final DaoFactory daoFactory;

    /**
     * @param daoFactory
     */
    public TiendaDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        // TODO Auto-generated constructor stub
    }

}
