<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Form</title>
    </head>
    <body>
        <h1>Contact Information Form</h1>
        <sf:form method="POST" modelAttribute="contact" action="updateContact">
            <sf:label path="name">Name:</sf:label><sf:input path="name" size="15" />
            <sf:errors path="name" cssclass="error"></sf:errors><br/>
            
            <sf:label path="phone">Phone:</sf:label><sf:input path="phone" size="15"/>
            <sf:errors path="phone" cssclass="error"></sf:errors><br/>
            
            <sf:label path="email">Email:</sf:label><sf:input path="email" size="15" />
            <sf:errors path="email" cssclass="error"></sf:errors><br/>
            
            <sf:hidden path="contactId" />
            <input type="submit" value="Update Contact" /><br/>
            
        </sf:form>
    </body>
</html>
