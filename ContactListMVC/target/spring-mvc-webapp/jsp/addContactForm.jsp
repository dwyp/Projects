<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Form</title>
    </head>
    <body>
        <h1>Contact Information Form</h1>
        
        <form method="POST" action="addContact">
            Name: <input type="text" name="contactName"/><br/>
            Phone: <input type="text" name="contactPhone"/><br/>
            Email: <input type="text" name="contactEmail"/><br/>
            <input type="submit" value="Add Contact"/><br/>
        </form>
    </body>
</html>
