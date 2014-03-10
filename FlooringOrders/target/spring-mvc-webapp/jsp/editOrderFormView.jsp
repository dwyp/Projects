<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Modal</title>
    </head>
    <body>
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Edit Order</h4>
        </div>
        <div class="modal-body">
            <p>
                <sf:form method="POST" commandName="order" action="editOrder">
                    <sf:label path="customerName">Customer Name:&nbsp;</sf:label><sf:input path="customerName" size="15" /><br/>
                    <sf:label path="state">State:&nbsp;</sf:label>
                    <sf:select path="state" items="${states}">State:&nbsp;</sf:select><br/>
                    <sf:label path="taxRate">Tax Rate:&nbsp;</sf:label><sf:input path="taxRate" size="6" /><br/>
                    <sf:label path="productType">Product Type:&nbsp;</sf:label>
                    <sf:select path="productType" items="${productTypes}"></sf:select><br/>
                    <sf:label path="area">Area:&nbsp;</sf:label><sf:input path="area" size="10"/><br/>
                    <sf:label path="costPerSqFt">Cost per square foot:&nbsp;</sf:label><sf:input path="costPerSqFt" size="6" /><br/>
                    <sf:label path="laborCostPerSqFt">Labor cost per square foot:&nbsp;</sf:label><sf:input path="laborCostPerSqFt" size="6" /><br/>
                    <sf:hidden path="orderNumber"/>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Discard</button>
                </div>
            </sf:form>
        </p>
    </div>
</body>
</html>
