'use strict';

let movieApi = (()=>{
    function getDailyMovie(param, callback) {
        $.ajax({
            url: '/movie/getDailyMovie',
            method: 'GET',
            data: param,
            contentType: "application/json",
            dataType: 'json'
        }).then((response) => {
            callback(response);
        });
    }

    return {
        getDailyMovie: getDailyMovie
    }
})();


