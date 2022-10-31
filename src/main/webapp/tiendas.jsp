<%-- 
    Document   : consulta
    Created on : 26 ene. 2021, 20:09:04
    Author     : Adrián Perera en CIFPCM
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<%@page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Servlet BuscadorIdTiendaServlet</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    </head>
    <body>

        <h1>Tiendas Disponibles:</h1>
        <img src="./img/Euro-Invoice-alt.png">
        <form action="buscadoridtienda" method="GET">
            <label for="listaTiendas">Elige una de las tiendas disponibles: </label>
            <select name="listaTiendas" id="listaTiendas">
                <c:forEach items="${listaTiendasBean}" var="tienda">
                    <option value="${tienda.idTienda}"><c:out value="${tienda.nombreTienda}"/></option>
                </c:forEach>
            </select>
            <input type="submit" class="btn-1" value="Enviar"/>
        </form>
    </body>
</html>
