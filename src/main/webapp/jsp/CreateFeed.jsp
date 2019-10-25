<%-- 
    Document   : CreateFeed
    Created on : 2019-10-24, 4:37:49
    Author     : lenovo
--%>

<%@page import="entity.Feed"%>
<%@page import="logic.FeedLogic"%>
<%@page import="entity.Host"%>
<%@page import="java.util.List"%>
<%@page import="logic.HostLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Feed Page</title>
    </head>
    <body>
        <h1>Create Feed!</h1>
        <%
            if(request.getParameter("submit")!=null){
                try{
                    FeedLogic fLogic = new FeedLogic();
                    if(fLogic.getWithPath(request.getParameter(FeedLogic.PATH))!=null){
                        out.print("<h1>Feed path already exists</h1>");
                    }else{
                        Feed f = fLogic.createEntity(request.getParameterMap());
                        fLogic.add(f);
                    }
                }catch(Exception e){
                    out.print("<h3>"+e.getMessage()+"</h3>");
                }
            }
        %>
        <form method="post">
            <table align="center" >
                <tr>
                    <td>Host ID : </td>
                    <td>
                        <select id="host_id" name="<% out.print(FeedLogic.HOST_ID);%>">
                            <%
                                HostLogic hostLogic = new HostLogic();
                                List<Host> hosts = hostLogic.getAll();
                                for(Host host:hosts){
                                    out.print("<option value='"+ host.getId() +"'>"+host.getName()+"</option>");
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr><td>Path</td><td><input type="text" id="path" name="<% out.print(FeedLogic.PATH);%>" /></td></tr>
                <tr><td>Type</td><td><input type="text" id="type" name="<% out.print(FeedLogic.TYPE);%>" /></td></tr>
                <tr><td>Name</td><td><input type="text" id="name" name="<% out.print(FeedLogic.NAME);%>" /></td></tr>
                <tr><td><input type="submit" id="submit" name="submit" value="Submit"/></td><td><input type="reset" id="cancel" name="cancel" value="Cancel"/></td></tr>
            </table>
        </form>
    </body>
</html>
