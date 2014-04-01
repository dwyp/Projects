<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact List</title>
    </head>
    <body>
        <h1>Company Contacts</h1>
        <!-- #1 - Personalized welcome message -->
        Hello <sec:authentication property="principal.username" />!<br/>
        <!-- #2 - Logout link -->
        <a href="<c:url value="../j_spring_security_logout" />" > Logout</a><br/>
        <a href="../index.jsp">Home</a><br/>
        <!-- #3 - Only render this link if user has admin role -->
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <a href="displayNewContactForm">Add a Contact</a><br/>
        </sec:authorize>
        <hr/>
        
        <!-- Iterate over contactList: forEach contact in contactList, do something -->
        <c:forEach var="contact" items="${contactList}">
            <!-- Build custom delete url for each contact -->
        <s:url value="deleteContact"
               var="deleteContact_url">
            <s:param name="contactId" value="${contact.contactId}" />
        </s:url>
            <!-- Build custom edit url for each contact -->
        <s:url value="editContactForm"
               var="editContact_url">
            <s:param name="contactId" value="${contact.contactId}" />
        </s:url>
            <c:if test="${contact.name == 'John Doe'}">
                CEO<br/>
            </c:if>
            Name: ${contact.name} 
            <!-- #4 - Only render Edit and Delete links for users with admin role -->
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            | <a href="${deleteContact_url}">Delete</a> | 
              <a href="${editContact_url}">Edit</a>
            </sec:authorize>
            <br/>
            Phone: ${contact.phone}<br/>
            Email: ${contact.email}<br/>
            <hr>
        </c:forEach>
    </body>
</html>
