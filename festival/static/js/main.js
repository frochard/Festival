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

    $("#AfficherFestivaliers-form button").click( function() {
        var users_table = $('#festivaliers-table tbody');

        $.ajax({
            type: "get",
            url: "/people/",
            success: function(data){
                console.log(data);
                $.each(data, function (item) {
                    var id = data[item].id;
                    var url = data[item].url;
                    var stat_url = data[item].stat_url;

                    users_table.append(
                    '<tr>' +
                        '<td>' + id + '</td>' +
                        '<th><a href="' + url + '">' + id + '</a></th>' +
                        '<td><a type="button" class="btn btn-success btn-xs" href = "'+ stat_url + '">stats</a> ' +
                    '</tr>'
                    );


                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("ajax error");
            },
            dataType: "json",
            contentType : "application/json"
        });
    });

    if($('#festivaliers-table').length) {
        var users_table = $('#festivaliers-table tbody');

        $.ajax({
            type: "get",
            url: "/people/",
            success: function(data){
                console.log(data);
                $.each(data, function (item) {
                    var id = data[item].id;
                    var url = data[item].url;
                    var stat_url = data[item].stat_url;

                    users_table.append(
                    '<tr>' +
                        '<td>' + id + '</td>' +
                        '<th><a href="' + url + '">' + id + '</a></th>' +
                        '<td><a type="button" class="btn btn-success btn-xs" href = "'+ stat_url + '">stats</a> ' +
                    '</tr>'
                    );


                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log("ajax error");
            },
            dataType: "json",
            contentType : "application/json"
        });
    }

    if($('#stats-list').length) {
        var stats_list = $('ul#stats-list');

        $.ajax({
            type: "get",
            url: window.location.href,
            success: function(data){
                console.log(data);
                if(data.length == 0) {
                  stats_list.append('<li>Aucun etat</li>');
                }
                else {
                  $.each(data, function (item) {
                      var libelle_etat = data[item].libelle_etat;
                      var date_etat = data[item].date_etat;
                      stats_list.append(
                    		  '<tr>' +
                              	'<td>' + libelle_etat + '</td>' +
                              	'<td>' + date_etat + '</td>' +
                              '</tr>'
                      );
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

    $("#stats-form button").click( function() {
        $.ajax({
            type: "get",
            url: "/stats/",
            success: function(data){
                console.log(data);
                window.location = "/stats";
            },
            dataType: "json",
            contentType : "application/json"
        });
    });


});
