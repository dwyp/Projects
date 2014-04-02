<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">

        <title>Contact List</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/starter-template.css" rel="stylesheet">

    </head>

    <body style="">

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"/>Contact List</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>


        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div>
                        <h2>Add Contact</h2>
                        Name: <input id="add-name" type="text"/><br/>
                        Phone: <input id="add-phone" type="text"/><br/>
                        Email: <input id="add-email" type="text"/><br/>
                        <input id="addButton" type="submit" value="Add"/><br/>
                        <hr/>
                    </div>
                    <div>
                        <h2>Edit Contact</h2>
                        Name: <input id="edit-name" type="text"/><br/>
                        Phone: <input id="edit-phone" type="text"/><br/>
                        Email: <input id="edit-email" type="text"/><br/>
                        <input id="edit-contactId" type="hidden"/>
                        <input id="editButton" type="submit" value="Update" /><br/>
                        <hr/>
                    </div>
                    <div>
                        <h2>Contact Details</h2>
                        Search for Contact by ID: <input id="search-id" type="text"/> <input id="searchButton" type="submit" value="Search"/><br/>
                        <h4>Results</h4>
                        <p>ID: <span id="contact-id"/></p>
                        <p>Name: <span id="contact-name"/></p>
                        <p>Email: <span id="contact-email"/></p>
                        <p>Phone: <span id="contact-phone"/></p>
                    </div>
                </div>
                <div class="col-md-6">
                    <div>
                        <h2>Contacts</h2>
                        <div id="contact-list"></div>
                    </div>
                </div>
            </div>
        </div><!-- /.container -->


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/contact.js"></script>


    </body>
</html>