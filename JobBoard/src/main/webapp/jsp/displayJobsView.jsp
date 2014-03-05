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
        <link  href="/JobBoard/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <!-- Carousel -->
        <link  href="/JobBoard/css/carousel.css" rel="stylesheet" media="screen">
        <!­­ Tables ­­>
        <link  href="/JobBoard/css/table.css" rel="stylesheet" media="screen">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OSU Jobs</title>
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
                    <a class="navbar-brand" href="#">OSU Job Board</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="../index.html">Home</a></li>
                        <li><a href="#">Top</a></li>
                        <li><a href="#bottom">Bottom</a></li>
                    </ul>
                    <form class="navbar-form pull-right" role="search" method="POST" action="search">
                        <input class="form-control" type="text" name="keywords" 
                               value="${keywords}" placeholder="Enter keywords here">
                    </form>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div id="myCarousel" class="carousel slide">
            <div class="carousel-inner">
                <div class="item active">
                    <img src="images/Buckeyes.jpg"  alt="">
                    <div class="container">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="images/home_osu.gif"  alt="">
                    <div class="container">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="images/OhioState_Logo.jpg" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>

            <div id="carouselButtons">
                <button id="playButton" type="button" class="btn btn-default btn-xs">
                    <span class="glyphicon glyphicon-play"></span>
                </button>
                <button id="pauseButton" type="button" class="btn btn-default btn-xs">
                    <span class="glyphicon glyphicon-pause"></span>
                </button>
            </div>
        </div> 
    </div><!-- /.carousel -->

    <div class="text-center" id="search">
        <h2>Click on the jobs below for more details</h2>
        <h2>or enter keywords for an OR search:</h2>
        <form method="POST" action="search">
            <input type="text" name="keywords" value="${keywords}" 
                   placeholder="Enter keywords here" size="40">
            <input type="submit" value="Submit"> <br/><br/>
        </form>
        <form method="GET" action="displayJobs">
            <input type="submit" value="Clear Search"> <br/><br/>
        </form>
    </div>

    <div class="container" style="padding-bottom:200px">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#current" data-toggle="tab">Current Jobs</a></li>
            <li><a href="#expired" data-toggle="tab">Expired Job Postings</a></li>
        </ul>
        <div id="tabContent" class="tab-content">
            <div class="tab-pane active" id="current">
                <table class="table table-striped table-autosort:0 table-autofilter">
                    <thead>
                        <tr>
                            <th class="table-sortable:default table-filterable">Title</th>
                            <th class="table-sortable:default table-filterable">Department</th>
                            <th class="table-sortable:numeric">Job Number</th>
                            <th>Posted Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="job" items="${jobs}">
                            <tr class="clickableRow" data-toggle="modal" data-target="#modal_${job.number}">
                                <td style="text-align: left"><b>${job.title}</b></td>
                                <td style="text-align: left">${job.author}</td>
                                <td>${job.number}</td>
                                <td>${job.published}</td>
                            </tr>
                        <div class="modal fade" id="modal_${job.number}" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h3 class="modal-title">
                                            <a href="http://www.jobsatosu.com/postings/${job.number}" target="_blank">${job.title}</a> 
                                        </h3>
                                        <p>(open a new tab to the OSU job post)<p>
                                    </div>
                                    <div class="modal-body">
                                        <p>${job.content}</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="tab-pane" id="expired">
                <table class="table table-striped table-autosort:0 table-autofilter">
                    <thead>
                        <tr>
                            <th class="table-sortable:default table-filterable">Title</th>
                            <th class="table-sortable:default table-filterable">Department</th>
                            <th class="table-sortable:numeric">Job Number</th>
                            <th>Posted Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="job" items="${expiredJobs}">
                            <tr class="clickableRow" data-toggle="modal" data-target="#modal_${job.number}">
                                <td style="text-align: left"><b>${job.title}</b></td>
                                <td style="text-align: left">${job.author}</td>
                                <td>${job.number}</td>
                                <td>${job.published}</td>
                            </tr>
                        <div class="modal fade" id="modal_${job.number}" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h3 class="modal-title">${job.title}</h3>
                                    </div>
                                    <div class="modal-body">
                                        <p>${job.content}</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal-dialog -->
                        </div><!-- /.modal -->
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>            
    <div id="bottom"/>
    <!­­ jQuery (necessary for Bootstrap's JavaScript plugins) ­­>
    <script src="/JobBoard/js/jquery.js"></script>
    <!­­ Include all compiled plugins, or include individual files as needed ­­>
    <script src="/JobBoard/js/bootstrap.js"></script>
    <script src="/JobBoard/js/table.js"></script>
    <script type="text/javascript">
        $(function() {
            $('#myCarousel').carousel({
                interval: 5000,
                pause: "false"
            });
            $('#playButton').click(function() {
                $('#myCarousel').carousel('cycle');
            });
            $('#pauseButton').click(function() {
                $('#myCarousel').carousel('pause');
            });
        });
    </script> 
</body>
</html>
