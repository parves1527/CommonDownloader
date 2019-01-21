<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>File List</title>
        <meta charset="utf-8">				
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/index.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script src='resources/index.js' type="text/javascript"></script>
    </head>

    <body>

        <h1>Files</h1>
        <table>
            <thead>
                <tr>
                    <th>File</th>
                    <th>Size</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <button name="download" class="btn btn-primary" id="download">Download</button>

        <div>
            <div id="file_viewer" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Confirmation</h4>
                        </div>
                        <div class="modal-body">
                            <img id="container_image" width="200" height="200" >
                            <iframe id="container_html" height="200" width="200" ></iframe> 
                            <pre id="container_text" ></pre>
                        </div>
                        <div class="modal-footer">							
                            <button name="approve" class="btn btn-success" id="approve_button" >Approve</button>
                            <button name="reject" class="btn btn-danger" id="reject_button" >Reject</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>		
            
            <div id="request_box" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Download URL</h4>
                        </div>
                        <div class="modal-body">
                            <input type="text" placeholder="<protocol://><host-name>/<file-name>" id="request_input" />
                        </div>
                        <div class="modal-footer">							
                            <button name="approve" class="btn btn-success" id="request_button">Download</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
                
    </body>
</html>