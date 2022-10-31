<%-- 
    Document   : consulta
    Created on : 26 ene. 2021, 20:09:04
    Author     : Adrián Perera en CIFPCM
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<%@page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="tiendaBean" class="es.cifpcm.ejer2_pererahernandezadrian.model.Tienda" scope="application"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Servlet BuscadorIdTiendaServlet</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    </head>
    <body>

        <h1>Información de ${tiendaBean.nombreTienda}</h1>
        <table>
            <tr>
                <th>Id Tienda</th>
                <th>Nombre Tienda</th>
                <th>Dirección</th>
                <th>Localidad</th>
                <th>Teléfono</th>
            </tr>
            <tr>
                <td><jsp:getProperty name="tiendaBean" property="idTienda"/></td>
                <td><c:out value="${tiendaBean.nombreTienda}"/></td>
                <td><%out.println(tiendaBean.getDireccion());%></td>
                <td>${tiendaBean.localidad}</td>
                <td>${tiendaBean.telefono}</td>
            </tr>
        </table>
        <h2>Facturas de ${tiendaBean.nombreTienda}</h2>
        <table>
            <tr>
                <th>Numero de factura</th>
                <th>Fecha</th>
                <th>Cliente</th>
                <th>Detalles</th>
            </tr>
            <c:forEach items="${facturaBean}" var="factura">
                <tr>
                    <td>${factura.nFactura}</td>
                    <td>${factura.fecha}</td>
                    <td>${factura.cliente}</td>
                    <td>
                        <form action="detallesfactura" method="POST">
                            <input type="hidden" name="invoiceToSearch" value="${factura.nFactura}"/>
                            <input type="submit" class="btn-1" value="Ver detalles"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2>El total vendido en esta tienda es de: ${totalBean} ¤</h2>
        <a href="${pageContext.request.contextPath}/tiendas.jsp"><button class="btn-1">Volver a buscar</button></a>
    </body>
</html>
