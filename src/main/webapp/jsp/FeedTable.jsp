<%-- 
    Document   : FeedTable
    Created on : 2019-10-20, 6:39:15
    Author     : lenovo
--%>

<%@page import="entity.Feed"%>
<%@page import="java.util.List"%>
<%@page import="logic.FeedLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feed Table Page</title>
    </head>
    <body>
        <h1>Feed Table Servlet!</h1>
        <table align="center" border="1">
            <thead>
                <%
                    out.print("<tr>");
                    FeedLogic fl = new FeedLogic();
                    for(String colName:fl.getColumnNames()){
                        out.print("<th>"+colName+"</th>");
                    }
                    out.print("</tr>");
                %> 
            </thead>
            <tbody>
                <%
                    List<Feed> feeds = fl.getAll();
                    for(Feed feed:feeds){
                        out.print("<tr>");
                        out.print("<td>"+feed.getId()+"</td>");
                        out.print("<td>"+feed.getHostid().getId()+"</td>");
                        out.print("<td>"+feed.getPath()+"</td>");
                        out.print("<td>"+feed.getType()+"</td>");
                        out.print("<td>"+feed.getName()+"</td>");
                        out.print("</tr>");
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
