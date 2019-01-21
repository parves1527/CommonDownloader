function show_file_viewer(id, type, src) {
    var file_viewer = $('#file_viewer');

    $("#container_image").hide();
    $("#container_html").hide();
    $("#container_text").hide();    

    $.ajax({
        type : "get",
        url : "get",
        data : {
            "id" : id
        },        
        success: function(data) {
            console.log("success 16");
            if(data.type === 'image') {
                $("#container_image").attr('src', data.content);
                $("#container_image").show();
            }
            else if(data.type === 'html') {
                $("#container_html").contents().find('body').html(data.content);
                $("#container_html").show();
            }
            else if(data.type === 'text') {
                $("#container_text").text(data.content);
                $("#container_text").show();
            }                          
        },
        error: function(req, status, err) {
            console.log("error 30");
        }
    });        

    file_viewer.modal('show');
}

function add_row(item) {
    var inputTag = "<input type='button' value='open' class='btn' onclick='show_file_viewer(\"" + item.id + "\", \"" + item.type + "\", \"" + item.src + "\")'>";
    var markup = "<tr id=\"tr" + item.id + "\"></td><td>" + item.file + "</td><td>" + item.size + "</td><td>" + inputTag + "</td></tr>";
    $("table tbody").append(markup);
}

function retrieve_files() {
    console.log("retrieve_files in");
    $('table tbody').empty();
    
    $.ajax({
        type : "get",
        url : "show",
        data : {
        //"id" : ${articleCount}
        },
        success: function(data){
            //console.log('success');
            data.forEach(function(item) {
                add_row(item);
            });                        
        },
        error: function(req, status, err) {
            console.log("error");
        }
    });
}

function update_file_state(event) {         
    $.ajax({
        type : "get",
        url : "update",
        data : {
            "state" : event.data.state 
        },
        success: function(data){
            console.log('approve/reject success: ' + event.data.state);
            retrieve_files();
            $("#file_viewer").modal('hide');     
        },
        error: function(req, status, err) {
            console.log('approve/reject error: ' + event.data.state);
            $("#file_viewer").modal('hide');
        }
    });    
}

$(document).ready(function(){
    
    $("#download").click(function() {
        $("#request_box").modal('show');
    });
    
    $("#request_button").click(function() {
        $.ajax({
            type : "get",
            url : "download",
            data : {
                "id" : $("#request_input").val()                
            },
            success: function(data){
                console.log('success');
                $("#request_box").modal('hide');
            },
            error: function(req, status, err) {
                console.log("error");
            }
        });
    });
    
    $("#approve_button").click({state: true}, update_file_state);
    $("#reject_button").click({state: false}, update_file_state);

    retrieve_files();

    //setInterval(add_row, 10000);
});