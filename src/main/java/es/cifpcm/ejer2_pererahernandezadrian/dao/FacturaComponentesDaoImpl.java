/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.dao;

import es.cifpcm.ejer2_pererahernandezadrian.model.FacturaComponentes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class FacturaComponentesDaoImpl implements FacturaComponentesDao {

    //Logger logger = LoggerFactory.getLogger(TiendaDaoImpl.class);
    private final DaoFactory daoFactory;

    /**
     * @param daoFactory
     */
    public FacturaComponentesDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<FacturaComponentes> getFacturaComponentesDao(Integer nFactura) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DaoFactory.getInstance().getConnection();

            //STEP 4: Execute a query
            String sql = "SELECT * FROM facturacomponente WHERE nFactura=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nFactura);
            ResultSet rs = pstmt.executeQuery();

            listaFacturaComponentes.clear();
            //STEP 5: Extract data from result set
            while (rs.next()) {
                FacturaComponentes facturaComponentes = new FacturaComponentes();
                //Retrieve by column name
                int id = rs.getInt("Id");
                String referencia = rs.getString("Referencia");
                int cantidad = rs.getInt("Cantidad");
                float precioAplicado = rs.getFloat("PrecioAplicado");

                //Lo guardamos en un objeto facturaComponentes
                facturaComponentes.setId(id);
                facturaComponentes.setReferencia(referencia);
                facturaComponentes.setCantidad(cantidad);
                facturaComponentes.setPrecioAplicado(precioAplicado);
                System.out.println("Objeto facturacomponentes (para comprobar si se duplica)");
                System.out.println(facturaComponentes);
                listaFacturaComponentes.add(facturaComponentes);
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
        return listaFacturaComponentes;
    }
    private List<FacturaComponentes> listaFacturaComponentes = new ArrayList<>();

}
