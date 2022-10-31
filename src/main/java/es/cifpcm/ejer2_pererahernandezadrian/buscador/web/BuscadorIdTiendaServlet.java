/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.ejer2_pererahernandezadrian.buscador.web;

import es.cifpcm.ejer2_pererahernandezadrian.dao.DaoConfig;
import es.cifpcm.ejer2_pererahernandezadrian.dao.DaoFactory;
import es.cifpcm.ejer2_pererahernandezadrian.dao.FacturaComponentesDao;
import es.cifpcm.ejer2_pererahernandezadrian.dao.FacturaComponentesDaoImpl;
import es.cifpcm.ejer2_pererahernandezadrian.dao.FacturasTiendaDao;
import es.cifpcm.ejer2_pererahernandezadrian.dao.FacturasTiendaDaoImpl;
import es.cifpcm.ejer2_pererahernandezadrian.dao.TiendaDao;
import es.cifpcm.ejer2_pererahernandezadrian.dao.TiendaDaoImpl;
import es.cifpcm.ejer2_pererahernandezadrian.model.Factura;
import es.cifpcm.ejer2_pererahernandezadrian.model.FacturaComponentes;
import es.cifpcm.ejer2_pererahernandezadrian.model.Tienda;
import java.io.IOException;
import java.text.DecimalFormat;
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
 * @author AdriÃ¡n Perera en CIFPCM
 */
public class BuscadorIdTiendaServlet extends HttpServlet {

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
        // Creo la fÃ¡brica de objetos de acceso a datos
        DaoFactory.createInstance(daoCfg);
    }
    
    private final TiendaDao tiendaDaoPst = new TiendaDaoImpl(DaoFactory.getInstance());
    private static Tienda tienda = new Tienda();
    private final FacturasTiendaDao facturasTiendaDaoPst = new FacturasTiendaDaoImpl(DaoFactory.getInstance());
    private static List<Factura> listaFacturas = new ArrayList<>();
    private final FacturaComponentesDao facturaComponentesDaoPst = new FacturaComponentesDaoImpl(DaoFactory.getInstance());
    private static List<FacturaComponentes> listaFacturaComponentes = new ArrayList<>();
    private float total = 0.0f;
    private static final DecimalFormat df = new DecimalFormat("0.00");

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
        if (tienda.getIdTienda() != null) {
            getServletContext().setAttribute("tiendaBean", tienda);
            request.setAttribute("facturaBean", listaFacturas);
            request.setAttribute("totalBean", df.format(total));
            getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
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

        Integer numeroabuscar = null;
        try {
            numeroabuscar = Integer.parseInt(request.getParameter("listaTiendas"));
            System.out.println(numeroabuscar);
            //INMA: se podría hacer así­:
            //tienda = DaoFactory.getTiendaDao(numeroabuscar);
            tienda = tiendaDaoPst.getTiendaDao(numeroabuscar);
            //Ya tenemos el objeto tienda, que nos lo ha devuelto la interfaz.
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            tienda.setIdTienda(null);
        }

        //Mandamos el id de la tienda a la implementaciÃ³n de FacturasTiendaDao
        listaFacturas = facturasTiendaDaoPst.getFacturasTiendaDao(tienda.getIdTienda());

        total = 0.0f;
        //Hacemos una suma de los totales de las factura de la lista
        for (Factura factura : listaFacturas) {
            //INMA: Hace una consulta SQL dentro de éste bucle buscando la facturacomponente según el NFactura
            listaFacturaComponentes = facturaComponentesDaoPst.getFacturaComponentesDao(factura.getnFactura());
            for (FacturaComponentes facturaComponentes : listaFacturaComponentes) {
                total += (facturaComponentes.getPrecioAplicado() * facturaComponentes.getCantidad());
            }
        }
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
