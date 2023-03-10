//Requests

function load(type){
    let uri = "http://localhost:8080/" + type;
    let params = {
        method: "get"
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
            return response.json();
        })
        .then(function(json){
            console.log(json);
            if(type === "music"){
                json.forEach(song => displayMusic(song));
            }else{
                json.forEach(hp => displayHeadphones(hp));
            }
        });
}

function loadSingle(id, type){
    let uri = "http://localhost:8080/" + type + "/" + id;
    let params = {
        method: "get"
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
            return response.json();
        })
        .then(function(json){
            console.log(json);
            if(type === "music"){
                displayMusic(json);
            }else{
                displayHeadphones(json);
            }
        });
}

function loadRating(song){
    let uri = "https://cors-anywhere.herokuapp.com/https://api.musixmatch.com/ws/1.1/matcher.track.get" +
        "?apikey=4c22e98de99e5470368189fe6d2c48fe&q_track=" + song.name + "&q_artist=" + song.songWriter;
    let params = {
        method: "get",
        headers: {
            "Origin" : "localhost:8080"
        }
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
            return response.json();
        })
        .then(function(json){
            console.log(json);
            let ratingValue = json.message.body.track.track_rating;
            console.log(ratingValue);

            let rating = document.getElementById("RatingTD" + song.id);

            rating.innerHTML = ratingValue;
        });
}

function loadLyrics(id){
    let uri = "http://localhost:8080/music/" + id;
    let params = {
        method: "get"
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
            return response.json();
        })
        .then(function(json){
            console.log(json);
            displayLyrics(json);
        });
}

function displayLyrics(song){
    let uri = "https://cors-anywhere.herokuapp.com/https://api.musixmatch.com/ws/1.1/matcher.lyrics.get" +
        "?apikey=4c22e98de99e5470368189fe6d2c48fe&q_track=" + song.name + "&q_artist=" + song.songWriter;
    let params = {
        method: "get",
        headers: {
            "Origin" : "localhost:8080"
        }
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
            return response.json();
        })
        .then(function(json){
            console.log(json);
            let lyrics = json.message.body.lyrics.lyrics_body;
            console.log(lyrics);

            let lyricsID = document.getElementById("lyric-id");
            let lyricsArea = document.getElementById("lyric-body");

            lyricsID.innerHTML = "Lyrics (" + song.id + ")";
            lyricsArea.innerHTML = lyrics.replace(/(?:\r\n|\r|\n)/g, '<br>');
        });
}

function add(type, jsobj){
    let uri = "http://localhost:8080/" + type;
    let params = {
        method: "post",
        body: JSON.stringify(jsobj),
        headers: {
            "Content-Type": "application/json"
        }
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
            let id = jsobj.id;
            loadSingle(id, type);
        })
}

function save(type, jsobj){
    let uri = "http://localhost:8080/" + type;
    let params = {
        method: "put",
        body: JSON.stringify(jsobj),
        headers: {
            "Content-Type": "application/json"
        }
    };

    fetch(uri, params)
        .then(function(response){
            console.log(response);
        })
}

function deleteRecord(type, id){
    let uri = "http://localhost:8080/" + type + "/" + id;
    let params = {
        method: "delete"
    };

    fetch(uri, params)
        .then(function(){
            document.getElementById(id).remove();
        });
}