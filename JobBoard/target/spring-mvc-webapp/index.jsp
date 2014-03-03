<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta  name="viewport" content="width=device­width, initial­scale=1.0">

        <!­­ Bootstrap ­­>
        <link  href="/FlooringMasteryWeb/css/bootstrap.min.css" rel="stylesheet" media="screen">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OSU Jobs</title>
    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">OSU Job Board</a>
                </div>
                <div class="navbar-collapse collapse">
                </div><!--/.navbar-collapse -->
            </div>
        </div>

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1>Hello!</h1>
                <p>This project was created as part of the interview process at The Ohio State University.
                It is a full stack web application, using MySQL, Java, the Spring Framework, HTML, CSS, JavaScript, JQuery and Bootstrap.</p>
                <p><a class="btn btn-primary btn-lg" href="/JobBoard/spring/loadFeed" role="button">See project &raquo;</a></p>
                <p><a class="btn btn-primary btn-lg" href="https://github.com/dwyp/Projects/tree/master/JobBoard" role="button">See gitHub &raquo;</a></p>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4">
                    <h2>MySQL</h2>
                    <p>The MySQL database is accessed by a Spring dependency injected DAO using JDBCTemplate.  It contains all the jobs ever read by
                    the feed reader.  Expired jobs are shown in their own tab on the view.  The database is also used for keyword searches.</p>
                    
                </div>
                <div class="col-md-4">
                    <h2>Java and Spring</h2>
                    <p>The code was written in Java, driven by Agile methodologies and Test Driven Development.  Tests were 
                        written for all implementations of the DAO and logic classes and a test database was created and used.  
                        The ROME tools library was used for reading the ATOM feed from OSU.  Spring is used as the MVC, 
                        injecting dependancies and handling controller aspects.</p>
                </div>
                <div class="col-md-4">
                    <h2>HTML, CSS, JavaScript</h2>
                    <p>The view is written with HTML, CSS and JavaScript.  Plugins and stylesheets from JQuery and Bootstrap are used.</p>
                </div>
            </div>

            <hr>

            <footer>
                <p>&copy;2014</p>
            </footer>
        </div> <!-- /container -->
        <!­­ jQuery (necessary for Bootstrap's JavaScript plugins) ­­>
        <script src="/JobBoard/js/jquery.js"></script>
        <!­­ Include all compiled plugins, or include individual files as needed ­­>
        <script src="/JobBoard/js/bootstrap.js"></script>
    </body>
</html>
