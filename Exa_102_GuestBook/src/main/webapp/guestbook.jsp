<%@page import="at.eliastrummer.beans.GuestBookEntry"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guestbook</title>
    </head>
    <body style="background: dodgerblue;">
        <h1>Welcome to our guest book!</h1>
        <form action="GuestBookController" method="POST">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Nickname:</td>
                        <td><input name="nickname" value="spiderman" type="text" /></td>
                    </tr>
                    <tr>
                        <td>E-Mail:</td>
                        <td><input name="email" value="spiderman@marvel.com" type="text" /></td>
                    </tr>
                    <tr>
                        <td>Command:</td>
                        <td>
                            <textarea name="command">Create guestbook</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td><button type="submit">Submit</button></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br>
        <hr>
        <%
            List<GuestBookEntry> entries = (List<GuestBookEntry>) request.getAttribute("guestbookEntries");
            
            if(entries != null) {
                for(GuestBookEntry entry : entries) {
                    out.println(entry);
                }
            }
        %>
    </body>
</html>