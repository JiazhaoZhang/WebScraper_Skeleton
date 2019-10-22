<%-- 
    Document   : CreateAccount
    Created on : 2019-10-20, 9:25:38
    Author     : lenovo
--%>

<%@page import="entity.Account"%>
<%@page import="logic.AccountLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    </head>
   
    <body>
         <%
            if(request.getParameter("submit")!=null){
                String displayName = request.getParameter(AccountLogic.DISPLAY_NAME);
                String user = request.getParameter(AccountLogic.USER);
                String password = request.getParameter(AccountLogic.PASSWORD);
                AccountLogic al = new AccountLogic();
                try{
                    if(al.getWithDisplayName(displayName)==null&&al.getWithUser(user)==null){
                        al.addAccount(request.getParameterMap());
                    }else{
                        out.println("<h3>User exist</h3>");
                    }
                }catch(RuntimeException e){
                    out.println("<h1>"+e.getMessage()+"</h1>");
                }
            }
         %>
        <h1>Create Account!</h1>
        <form id="createAccountForm" method="post">
        <table align="center">
            <tr>
                <td>Display_Name : </td><td><input id="display_name" name="display_name" /></td>
            </tr>
            <tr>
                <td>user : </td><td><input id="user" name="user" /></td>
            </tr>
            <tr>
                <td>password : </td><td><input id="password" name="password" /></td>
            </tr>
             <tr>
                 <td><button type="submit" id="submit" name="submit">Submit</button></td><td><button type="reset" id="cancel" name="cancel">Cancel</button></td>
            </tr>
        </table>
        </form>
    </body>
    <script>
//        $(document).ready(function(){
//            $("#submit").click(function(){
//                let displayName = $("#display_name").val();
//                let user = $("#user").val();
//                let password = $("#password").val();
//                alert(displayName+" "+user+" "+password );
//                
//                $.ajax({
//                type:"POST",
//                url:"http://localhost:8080/CreateHost",
//                data:$("#createAccountForm").serialize(),
//                error:function(){
//                    alert("error");
//                },
//                success:function(data){
//                    alert(data);
//                }
//                });
//            });
        });
    </script>
</html>
