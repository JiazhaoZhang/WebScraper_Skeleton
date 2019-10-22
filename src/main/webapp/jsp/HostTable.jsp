<%-- 
    Document   : HostTable
    Created on : 2019-10-20, 7:00:52
    Author     : lenovo
--%>

<%@page import="java.util.List"%>
<%@page import="entity.Host"%>
<%@page import="logic.HostLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Host Table Page</title>
    </head>
    <body>
        <h1>Host Table!</h1>
        <table align="center" border="1">
            <thead>
                <%
                    out.print("<tr>");
                    HostLogic hl = new HostLogic();
                    for(String colName:hl.getColumnNames()){
                        out.print("<th>"+colName+"</th>");
                    }
                    out.print("</tr>");
                %> 
            </thead>
            <tbody>
                <%
                    List<Host> hosts = hl.getAll();
                    for(Host host:hosts){
                        out.print("<tr>");
                        for( Object data:hl.extractDataAsList(host)){
                            out.print("<td>"+data+"</td>");
                        }
                        out.print("</tr>");
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
