<%-- 
    Document   : AccountTable
    Created on : 2019-10-20, 4:26:16
    Author     : lenovo
--%>

<%@page import="entity.Account"%>
<%@page import="logic.AccountLogic,java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Table Page</title>
    </head>
    <body>
        <h1>Account Table Servlet!</h1>
       
        <table align="center" border="1">
            <thead>
                <%
                    out.print("<tr>");
                    AccountLogic al = new AccountLogic();
                    for(String colName:al.getColumnNames()){
                        out.print("<th>"+colName+"</th>");
                    }
                    out.print("</tr>");
                %> 
            </thead>
            <tbody>
                <%
                    List<Account> accounts = al.getAll();
                    for(Account a:accounts){
                        out.print("<tr>");
                        out.print("<td>"+a.getId()+"</td>");
                        out.print("<td>"+a.getDisplayName()+"</td>");
                        out.print("<td>"+a.getUser()+"</td>");
                        out.print("<td>"+a.getPassword()+"</td>");
                        out.print("</tr>");
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
