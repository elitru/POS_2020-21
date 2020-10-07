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
        <form action="GuestBookController" method="POST" onsubmit="return validate();">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Nickname:</td>
                        <td><input id="nickname" name="nickname" value="spiderman" type="text" /></td>
                    </tr>
                    <tr>
                        <td>E-Mail:</td>
                        <td><input id="email" name="email" value="spiderman@marvel.com" type="text" /></td>
                    </tr>
                    <tr>
                        <td>Command:</td>
                        <td>
                            <textarea id="command" name="command">Create guestbook</textarea>
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
        <script src="index.js" type="text/javascript"></script>
    </body>
</html>