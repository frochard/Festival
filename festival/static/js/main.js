$(document).ready(function() {
    $("#create-festivalier-form button").click( function() {
        $.ajax({
            type: "post",
            url: "/people/",
            success: function(data){
                console.log(data);
                window.location = "/";
            },
            dataType: "json",
            contentType : "application/json"
        });
    });

    $("#create-bus-form button").click( function() {
        $.ajax({
            type: "post",
            url: "/buses/",
            success: function(data){
                console.log(data);
                window.location = "/";
            },
            dataType: "json",
            contentType : "application/json"
        });
    });

    if($('#users-table').length) {
        var users_table = $('#users-table tbody');

        $.ajax({
            type: "get",
            url: "/people/",
            success: function(data){
                console.log(data);

                $.each(data, function (item) {
                    var id = data[item].id;
                    var url = data[item].url;

                    users_table.append(
                    '<tr>' +
                        '<td>' + id + '</td>' +
                        '<th><a href="' + url + '">' + id + '</a></th>' +
                    '</tr>'
                    );

                    $('button#delete-user-' + id).click(function() {
                        $.ajax({
                            type: "delete",
                            url: "/users/" + id,
                            success: function(data){
                                console.log("User " + data.id + " deleted");
                                window.location = "/";
                            }
                        });
                    });

                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("ajax error");
            },
            dataType: "json",
            contentType : "application/json"
        });
    }

    if($('#tweets-list').length) {
        var tweets_list = $('ul#tweets-list');

        $.ajax({
            type: "get",
            url: window.location.href,
            success: function(data){
                console.log(data);
                if(data.length == 0) {
                  tweets_list.append('<li>Aucun tweet</li>');
                }
                else {
                  $.each(data, function (item) {
                      var tweet = data[item].content;
                      tweets_list.append('<li>' + tweet + '</li>');
                  });
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("ajax error");
            },
            dataType: "json",
            contentType : "application/json"
        });
    }
});
