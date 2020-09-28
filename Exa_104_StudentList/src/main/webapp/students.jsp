<%-- 
    Document   : students.jsp
    Created on : 28.09.2020, 08:26:07
    Author     : root
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="at.eliastrummer.beans.Student"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if(request.getAttribute("students") == null) {
        response.sendRedirect("./StudentsController");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/67bcba6f6a.js" crossorigin="anonymous"></script>    </head>
    <body>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Raleway:wght@100;200;300;400;500;600;700&display=swap');
            
            html,
            body {
                width: 100%;
                height: 100%;
                margin: 0px;
                padding: 30px;
                background: white;
                margin: 0 auto;
                box-sizing: border-box;
                font-family: 'Raleway';
            }
            
            * {
                font-family: 'Raleway' !important;
            }
            
            .student-list-container {
                width: 80%;
                height: auto;
                padding: 25px;
                box-sizing: border-box;
                background: #eee;
                box-shadow: 0px 0px 10px 0px #888;
                margin: 0 auto;
            }
            
            input,
            select {
                background: #fff;
                border: none;
                border-bottom: solid 1px #666;
                padding: 10px 12px;
                margin-right: 80px;
            }
            
            button {
                width: 180px;
                cursor: pointer;
                padding: 10px 0px;
                background: #03cebc;
                color: #fff;
                margin-right: 20px;
                color: #fff;
                border: none;
                transition: all 300ms;
            }
            
            button:hover {
                background: #00b0a0;
            }
            
            .name,
            select {
                width: 250px;
                display: inline-block;
            }
            
            .student-info {
                width: 80%;
                height: auto;
                padding: 25px;
                box-sizing: border-box;
                background: #eee;
                box-shadow: 0px 0px 10px 0px #888;
                margin: 0 auto;
                margin-top: 50px;
                display: flex;
                flex-direction: row;
                align-content: center;
                align-items: center;
                justify-content: space-between;
            }
            
            .image {
                width: 40%;
                margin: 0 auto;
            }
            
            .image img {
                width: 50%;
                height: auto;
                margin: 0 auto;
            }
            
            .info {
                width: 50%;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                color: #333;
            }
            
            .info section {
                padding: 6px 0px;
            }
        </style>
        <div class="student-list-container">
            <div class="filter">
                <form method="POST">
                    <input class="name" name="filter" value="" placeholder="Filter nach Namen" />
                    <button type="submit">Filtern</button>
                    <button type="reset">Filter zurücksetzen</button>
                    <div style="margin-top: 20px;">
                        <span style="padding-right: 10px;">Schüler auswählen: </span>
                        <select onchange="submit();" name="selected">
                            <%
                                List<Student> students = (ArrayList) request.getAttribute("students");
                                String filter = (String) request.getAttribute("filter");
                                Student selected = (Student) request.getAttribute("selected");
                                
                                if(students != null && filter != null) {
                                    for(Student student : students) {
                                        if(!student.getLastname().toLowerCase().contains(filter)) {
                                            continue;
                                        }
                                        if(selected != null && selected.getCatNo() == student.getCatNo() && selected.getClassName().equals(student.getClassName())) {
                                            out.println(String.format("<option value='%s;%d' selected>%s %s</option>", student.getClassName(), student.getCatNo(), student.getLastname(), student.getFirstname()));
                                        }else {
                                            out.println(String.format("<option value='%s;%d'>%s %s</option>", student.getClassName(), student.getCatNo(), student.getLastname(), student.getFirstname()));
                                        }
                                    }
                                }
                            %>
                        </select>
                    </div>
                </form>
            </div>
        </div>
        <div class="student-info">
            <div class="image">
                <img src="graduated.svg" />
            </div>
            <div class="info">
                <h1>Schülerinfo</h1>
                <%
                    Student student = (Student) request.getAttribute("selected");
                    
                    if(student != null) {
                        String template = "<section><b>%s:</b> %s</section>";
                        out.println(String.format(template, "Name", student.getFirstname() + " " + student.getLastname()));
                        out.println(String.format(template, "Katalognummer", (student.getCatNo() + "")));
                        out.println(String.format(template, "Klasse", student.getClassName()));
                        out.println(String.format(template, "Geschlecht", student.getGender()));
                        out.println(String.format(template, "Geburtsdatum", student.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                    }
                %>
            </div>
        </div>
    </body>
</html>
