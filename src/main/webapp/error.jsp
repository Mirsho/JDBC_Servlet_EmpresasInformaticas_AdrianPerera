<%-- 
    Document   : error
    Created on : 26 ene. 2021, 20:16:56
    Author     : Adrián Perera en CIFPCM
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-15"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    </head>
    <body>
        <h1>La tienda con el id introducido no existe.</h1>
        <a href="${pageContext.request.contextPath}/loginSuccess.jsp"><button class="btn-1">Volver a buscar</button></a>
    </body>
</html>
