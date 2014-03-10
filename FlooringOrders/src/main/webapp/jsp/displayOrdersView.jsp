<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!­­ Bootstrap ­­>
        <link  href="/FlooringOrders/css/bootstrap.min.css" rel="stylesheet" media="screen">

        <title>Flooring Orders</title>
    </head>

    <body style="padding-top:50px;">
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Flooring Orders</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="../index.jsp">Home</a></li>
                        <li><a href="#myAddModal" data-toggle="modal" data-target="#myAddModal">Add Order</a></li>
                        <li><a href="#">Top</a></li>
                        <li><a href="#bottom">Bottom</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div class="container">
            <h1 class="text-center">Flooring Orders</h1>
            <p class="text-center">There are a total of ${size} orders.<br/></p>
            <p class="text-center"><button class="btn btn-primary btn" data-toggle="modal" 
                                           data-target="#myAddModal">Add Order
                </button><br/><br/></p>

            <c:forEach var="order" items="${orders}">
                <div class="col-sm-6 col-md-4">
                    Order Number: ${order.orderNumber} 
                    | <a data-toggle="modal" href="editOrderForm?orderNumber=${order.orderNumber}" 
                         data-target="#myEditModal_${order.orderNumber}">Edit</a> 
                    | <a href="#myDeleteModal_${order.orderNumber}" data-toggle="modal" 
                         data-target="#myDeleteModal_${order.orderNumber}">
                         <span class="glyphicon glyphicon-trash"></span></a><br/>

                    Customer Name: ${order.customerName}<br/>
                    State: ${order.state}<br/>
                    Tax Rate: ${order.taxRate}%<br/>
                    Product Type: ${order.productType}<br/>
                    Area: ${order.area}<br/>
                    Cost per square foot: $${order.costPerSqFt}<br/>
                    Labor cost per square foot: $${order.laborCostPerSqFt}<br/>
                    Material cost: $${order.materialCost}<br/>
                    Labor cost: $${order.laborCost}<br/>
                    Tax: $${order.tax}<br/>
                    Total: $${order.total}<br/><br/>

                    <div id="myEditModal_${order.orderNumber}" class="modal fade">
                        <div class="modal-dialog">
                            <div class="modal-content">
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->

                    <div id="myDeleteModal_${order.orderNumber}" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">Delete Order</h4>
                                </div>
                                <div class="modal-body">
                                    <form method="POST" action="deleteOrder">
                                        Are you sure you wish to delete this order?
                                        <input type="hidden" value="${order.orderNumber}" name="id">
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                        </div>
                                    </form>
                                </div>
                            </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                    </div><!-- /.modal -->
                </div>
            </c:forEach>


            <div id="myAddModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Add Order</h4>
                        </div>
                        <div class="modal-body">
                            <p>
                            <form method="POST" action="addOrder">
                                Customer Name: <input type="text" name="customerName" /><br/>
                                State:
                                <select name="state">
                                    <c:forEach var="state" items="${states}">
                                        <option value="${state}">${state}</option>
                                    </c:forEach>
                                </select> 
                                <br/>
                                Product Type:  
                                <select name="productType">
                                    <c:forEach var="product" items="${productTypes}">
                                        <option value="${product.productType}">${product.productType}</option>
                                    </c:forEach>
                                </select>
                                <br/>
                                Area:  <input type="text" name="area"<br/>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Save</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Discard</button>
                                </div>
                            </form>
                            </p>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
        </div>
        <p id="bottom" />
    </body>

    <!­­ jQuery (necessary for Bootstrap's JavaScript plugins) ­­>
    <script src="/FlooringOrders/js/jquery.js"></script>
    <!­­ Include all compiled plugins, or include individual files as needed ­­>
    <script src="/FlooringOrders/js/bootstrap.js"></script>
</html>
