<%-- 
    Document   : detallesFactura
    Created on : 26 ene. 2021, 22:15:52
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
        <h2>Detalles de Factura</h2>
        <table>
            <tr>
                <th>Id Componente</th>
                <th>Referencia</th>
                <th>Cantidad</th>
                <th>Precio Aplicado</th>
            </tr>
            <c:forEach items="${facturaComponentesBean}" var="componente">
                <tr>
                    <td>${componente.id}</td>
                    <td>${componente.referencia}</td>
                    <td>${componente.cantidad}</td>
                    <td>${componente.precioAplicado}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="javascript:history.back()"><button class="btn-1">Volver a tienda</button></a>
    </body>
</html>

