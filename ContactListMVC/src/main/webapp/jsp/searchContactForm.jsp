<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Contact Form</title>
    </head>
    <body>
        <h1>Search Contacts</h1>
        
        <form method="POST" action="searchContacts">
            Name: <input type="text" name="contactName"/><br/>
            Phone: <input type="text" name="contactPhone"/><br/>
            Email: <input type="text" name="contactEmail"/><br/>
            <input type="submit" value="Search"/><br/>
        </form>
    </body>
</html>
