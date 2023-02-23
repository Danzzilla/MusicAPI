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