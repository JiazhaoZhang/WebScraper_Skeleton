<%-- 
    Document   : ImageTable
    Created on : 2019-10-20, 6:52:03
    Author     : lenovo
--%>

<%@page import="entity.Image"%>
<%@page import="java.util.List"%>
<%@page import="logic.ImageLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Table Page</title>
    </head>
    <body>
        <h1>Image Table!</h1>
        <table align="center" border="1">
            <thead>
                <%
                    out.print("<tr>");
                    ImageLogic il = new ImageLogic();
                    for(String colName:il.getColumnNames()){
                        out.print("<th>"+colName+"</th>");
                    }
                    out.print("</tr>");
                %> 
            </thead>
            <tbody>
                <%
                    List<Image> images = il.getAll();
                    for(Image image:images){ 
                        out.print("<tr>");
                       for(Object data:il.extractDataAsList(image)){
                        out.print("<td>"+data+"</td>");   
                       }
                        out.print("</tr>");
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
