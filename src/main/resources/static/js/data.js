//Initiators

window.onload = function(){
    load("music");
    load("headphones");
}

document.querySelector("#submit-music").addEventListener("click", event => {
    let jsobj = {};

    let name = document.querySelector("#name").value;
    let writer = document.querySelector("#writer").value;
    let length = document.querySelector("#length").value;
    let year = document.querySelector("#year").value;

    jsobj.name = name;
    jsobj.songWriter = writer;
    jsobj.length = length;
    jsobj.year = year;

    add("music", jsobj);
})

document.querySelector("#submit-headphones").addEventListener("click", event => {
    let jsobj = {};

    let brand = document.querySelector("#brand").value;
    let model = document.querySelector("#model").value;
    let price = document.querySelector("#price").value;
    let rating = document.querySelector("#rating").value;

    jsobj.brand = brand;
    jsobj.model = model;
    jsobj.price = price;
    jsobj.rating = rating;

    add("headphones", jsobj);
})

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
                displayMusic(json);
            }else if(type === "headphones"){
                displayHeadphones(json);
            }
        });
}

function loadSingle(name, type){
    let uri = "http://localhost:8080/" + type + "/" + name;
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
            }else if(type === "headphones"){
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
            let name = jsobj.model;
            if(type === "music"){
                name = jsobj.name;
            }
            loadSingle(name, type);
        })
}

//Functions

function displayMusic(music){
    let table = document.querySelector("#music-records tbody");

    //loop over records
    for(let i = 0; i < music.length; i++){
        let song = music;
        if(Array.isArray(music)){
            song = music[i];
        }

        let row = document.createElement("tr");

        let name = document.createElement("td");
        let writer = document.createElement("td");
        let length = document.createElement("td");
        let year = document.createElement("td");

        name.innerHTML = song.name;
        writer.innerHTML = song.songWriter;
        length.innerHTML = song.length;
        year.innerHTML = song.year;

        row.appendChild(name);
        row.appendChild(writer);
        row.appendChild(length);
        row.appendChild(year);

        table.appendChild(row);
        if(!Array.isArray(music)){
            return;
        }
    }
}

function displayHeadphones(hps){
    let table = document.querySelector("#headphones-records tbody");

    //loop over records
    for(let i = 0; i < hps.length; i++){
        let hp = hps;
        if(Array.isArray(hps)){
            hp = hps[i];
        }

        let row = document.createElement("tr");

        let brand = document.createElement("td");
        let model = document.createElement("td");
        let price = document.createElement("td");
        let rating = document.createElement("td");

        brand.innerHTML = hp.brand;
        model.innerHTML = hp.model;
        price.innerHTML = hp.price;
        rating.innerHTML = hp.rating;

        row.appendChild(brand);
        row.appendChild(model);
        row.appendChild(price);
        row.appendChild(rating);

        table.appendChild(row);

        if(!Array.isArray(hps)){
            return;
        }
    }
}