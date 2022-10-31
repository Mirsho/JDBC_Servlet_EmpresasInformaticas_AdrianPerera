/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.controller;

import es.cifpcm.ejer2_pererahernandezadrian.dao.DaoConfig;
import es.cifpcm.ejer2_pererahernandezadrian.dao.DaoFactory;
import es.cifpcm.ejer2_pererahernandezadrian.dao.ListaTiendasDao;
import es.cifpcm.ejer2_pererahernandezadrian.dao.ListaTiendasDaoImpl;
import es.cifpcm.ejer2_pererahernandezadrian.model.Tienda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adri�n Perera en CIFPCM
 */
public class ListarTiendasServlet extends HttpServlet {

    private DaoConfig daoCfg;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
        this.daoCfg = new DaoConfig();
        String configBundleName = config.getInitParameter("app.config");
        ResourceBundle rb = ResourceBundle.getBundle(configBundleName);
        daoCfg.setDriverClassName(rb.getString("database.driver"));
        daoCfg.setDatabaseUrl(rb.getString("database.url"));
        daoCfg.setDatabaseUser(rb.getString("database.user"));
        daoCfg.setDatabasePassword(rb.getString("database.password"));
        //logger.debug("Intentando carga del driver {}", daoCfg.getDriverClassName());
        // Creo la fábrica de objetos de acceso a datos
        DaoFactory.createInstance(daoCfg);
    }

    private final ListaTiendasDao listaTiendasDaoImpl = new ListaTiendasDaoImpl(DaoFactory.getInstance());
    private static List<Tienda> listaTiendas = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        getServletContext().setAttribute("listaTiendasBean", listaTiendas);
        getServletContext().getRequestDispatcher("/tiendas.jsp").forward(request, response);
}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Mandamos el id de la tienda a la implementación de FacturasTiendaDao
        listaTiendas = listaTiendasDaoImpl.getListaTiendasDao();
        System.out.println("Goodbye!");
        
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
