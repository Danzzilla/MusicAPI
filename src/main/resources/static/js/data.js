//Initiators

window.onload = function(){
    load("music");
    load("headphones");
}

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

//Functions

function displayMusic(music){
    let table = document.querySelector("#music-records tbody");

    //loop over records
    for(let i = 0; i < music.length; i++){
        let song = music[i];

        let row = document.createElement("tr");

        let id = document.createElement("td");
        let name = document.createElement("td");
        let writer = document.createElement("td");
        let length = document.createElement("td");
        let year = document.createElement("td");

        id.innerHTML = song.id;
        name.innerHTML = song.name;
        writer.innerHTML = song.songWriter;
        length.innerHTML = song.length;
        year.innerHTML = song.year;

        row.appendChild(id);
        row.appendChild(name);
        row.appendChild(writer);
        row.appendChild(length);
        row.appendChild(year);

        table.appendChild(row);
    }
}

function displayHeadphones(hps){
    let table = document.querySelector("#headphones-records tbody");

    //loop over records
    for(let i = 0; i < hps.length; i++){
        let hp = hps[i];

        let row = document.createElement("tr");

        let id = document.createElement("td");
        let brand = document.createElement("td");
        let model = document.createElement("td");
        let price = document.createElement("td");
        let rating = document.createElement("td");

        id.innerHTML = hp.id;
        brand.innerHTML = hp.brand;
        model.innerHTML = hp.model;
        price.innerHTML = hp.price;
        rating.innerHTML = hp.rating;

        row.appendChild(id);
        row.appendChild(brand);
        row.appendChild(model);
        row.appendChild(price);
        row.appendChild(rating);

        table.appendChild(row);
    }
}