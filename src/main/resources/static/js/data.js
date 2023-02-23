//Initiators

load("music");
load("headphones");

document.querySelector("#submit-music").addEventListener("click", event => {
    let jsobj = {};

    let name = document.querySelector("#name");
    let writer = document.querySelector("#writer");
    let length = document.querySelector("#length");
    let year = document.querySelector("#year");

    jsobj.name = name.value;
    jsobj.songWriter = writer.value;
    jsobj.length = length.value;
    jsobj.year = year.value;
    jsobj.id = Math.floor(Math.random() * Date.now());

    add("music", jsobj);

    document.querySelector("#name").value = "";
    document.querySelector("#writer").value = "";
    document.querySelector("#length").value = "";
    document.querySelector("#year").value = "";
})

document.querySelector("#submit-headphones").addEventListener("click", event => {
    let jsobj = {};

    let brand = document.querySelector("#brand");
    let model = document.querySelector("#model");
    let price = document.querySelector("#price");
    let rating = document.querySelector("#rating");

    jsobj.brand = brand.value;
    jsobj.model = model.value;
    jsobj.price = price.value;
    jsobj.rating = rating.value;
    jsobj.id = Math.floor(Math.random() * Date.now());

    add("headphones", jsobj);

    document.querySelector("#brand").value = "";
    document.querySelector("#model").value = "";
    document.querySelector("#price").value = "";
    document.querySelector("#rating").value = "";
})

function editMusicListener(button){
    button.addEventListener("click", event => {
        let row = event.target.value;
        console.log("click" + row);

        let name = document.getElementById("NameTD" + row);
        let writer = document.getElementById("WriterTD" + row);
        let length = document.getElementById("LengthTD" + row);
        let year = document.getElementById("YearTD" + row);
        let edit = document.getElementById("EditTD" + row);

        name.innerHTML = "<input id='Name" + row + "' type='text' value='" + name.innerText + "'>";
        writer.innerHTML = "<input id='Writer" + row + "' type='text' value='" + writer.innerText + "'>";
        length.innerHTML = "<input id='Length" + row + "' type='text' value='" + length.innerText + "'>";
        year.innerHTML = "<input id='Year" + row + "' type='text' value='" + year.innerText + "'>";
        edit.innerHTML = "<button class='save" + row + " btn-success'>Save</button>";

        document.querySelector(".save" + row).addEventListener("click", event => {
            let jsobj = {};

            jsobj.id = row;
            jsobj.name = document.getElementById("Name" + row).value;
            jsobj.songWriter = document.getElementById("Writer" + row).value;
            jsobj.length = document.getElementById("Length" + row).value;
            jsobj.year = document.getElementById("Year" + row).value;

            save("music", jsobj);

            name.innerHTML = jsobj.name;
            writer.innerHTML = jsobj.songWriter;
            length.innerHTML = jsobj.length;
            year.innerHTML = jsobj.year;
            edit.innerHTML = "<button class='editMusic btn-warning' value='" + row + "'>Edit</button>"

            editMusicListener(document.querySelector("#EditTD" + row + " .editMusic"));
            deleteMusicListener(document.querySelector("#DeleteTD" + row + " .deleteMusic"));
        })
    })
}

function editHPListener(button){
    button.addEventListener("click", event => {
        let row = event.target.value;

        console.log("click" + row);
        let brand = document.getElementById("BrandTD" + row);
        let model = document.getElementById("ModelTD" + row);
        let price = document.getElementById("PriceTD" + row);
        let rating = document.getElementById("RatingTD" + row);
        let edit = document.getElementById("EditTD" + row);

        brand.innerHTML = "<input id='Brand" + row + "' type='text' value='" + brand.innerText + "'>";
        model.innerHTML = "<input id='Model" + row + "' type='text' value='" + model.innerText + "'>";
        price.innerHTML = "<input id='Price" + row + "' type='text' value='" + price.innerText + "'>";
        rating.innerHTML = "<input id='Rating" + row + "' type='text' value='" + rating.innerText + "'>";
        edit.innerHTML = "<button class='save" + row + " btn-success'>Save</button>";

        document.querySelector(".save" + row).addEventListener("click", event => {
            let jsobj = {};

            jsobj.id = row;
            jsobj.brand = document.getElementById("Brand" + row).value;
            jsobj.model = document.getElementById("Model" + row).value;
            jsobj.price = document.getElementById("Price" + row).value;
            jsobj.rating = document.getElementById("Rating" + row).value;

            save("headphones", jsobj);

            brand.innerHTML = jsobj.brand;
            model.innerHTML = jsobj.model;
            price.innerHTML = jsobj.price;
            rating.innerHTML = jsobj.rating;
            edit.innerHTML = "<button class='editHeadphones btn-warning' value='" + row + "'>Edit</button>"

            editHPListener(document.querySelector("#EditTD" + row + " .editHeadphones"));
            deleteMusicListener(document.querySelector("#DeleteTD" + row + " .deleteHeadphones"));
        })
    })
}

function deleteMusicListener(button){
    button.addEventListener("click", event => {
        let row = event.target.value;

        let remove = document.getElementById("DeleteTD" + row);
        remove.innerHTML = "<button class='confirm" + row + " btn-danger'>Confirm</button>" +
            "<button class='cancel" + row + " btn-success'>Cancel</button>";

        document.querySelector(".confirm" + row).addEventListener("click", event => {
            deleteRecord("music", row);
        })

        document.querySelector(".cancel" + row).addEventListener("click", event => {
            remove.innerHTML = "<button class='deleteMusic btn-danger' value='" + row + "'>Delete</button>";
        })
    })
}

function deleteHPListener(button){
    button.addEventListener("click", event => {
        let row = event.target.value;

        let remove = document.getElementById("DeleteTD" + row);
        remove.innerHTML = "<button class='confirm" + row + " btn-danger'>Confirm</button>" +
            "<button class='cancel" + row + " btn-success'>Cancel</button>";

        document.querySelector(".confirm" + row).addEventListener("click", event => {
            deleteRecord("headphones", row);
        })

        document.querySelector(".cancel" + row).addEventListener("click", event => {
            remove.innerHTML = "<button class='deleteHeadphones btn-danger' value='" + row + "'>Delete</button>";
        })
    })
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

//Functions

function displayMusic(song){
    let table = document.querySelector("#music-records tbody");

    let row = document.createElement("tr");
    row.setAttribute("id", song.id);

    let id = document.createElement("td");
    id.setAttribute("id","IdTD" + song.id);
    let name = document.createElement("td");
    name.setAttribute("id","NameTD" + song.id);
    let writer = document.createElement("td");
    writer.setAttribute("id","WriterTD" + song.id);
    let length = document.createElement("td");
    length.setAttribute("id","LengthTD" + song.id);
    let year = document.createElement("td");
    year.setAttribute("id","YearTD" + song.id);
    let edit = document.createElement("td");
    edit.setAttribute("id","EditTD" + song.id);
    let remove = document.createElement("td");
    remove.setAttribute("id","DeleteTD" + song.id);

    id.innerHTML = song.id;
    name.innerHTML = song.name;
    writer.innerHTML = song.songWriter;
    length.innerHTML = song.length;
    year.innerHTML = song.year;
    edit.innerHTML = "<button class='editMusic btn-warning' value='" + song.id + "'>Edit</button>"
    remove.innerHTML = "<button class='deleteMusic btn-danger' value='" + song.id + "'>Delete</button>"

    row.appendChild(id);
    row.appendChild(name);
    row.appendChild(writer);
    row.appendChild(length);
    row.appendChild(year);
    row.appendChild(edit);
    row.appendChild(remove);

    table.appendChild(row);

    editMusicListener(document.querySelector("#EditTD" + song.id + " .editMusic"));
    deleteMusicListener(document.querySelector("#DeleteTD" + song.id + " .deleteMusic"));
}


function displayHeadphones(hp){
    let table = document.querySelector("#headphones-records tbody");

    let row = document.createElement("tr");
    row.setAttribute("id", hp.id);

    let id = document.createElement("td");
    id.setAttribute("id","IdTD" + hp.id);
    let brand = document.createElement("td");
    brand.setAttribute("id","BrandTD" + hp.id);
    let model = document.createElement("td");
    model.setAttribute("id","ModelTD" + hp.id);
    let price = document.createElement("td");
    price.setAttribute("id","PriceTD" + hp.id);
    let rating = document.createElement("td");
    rating.setAttribute("id","RatingTD" + hp.id);
    let edit = document.createElement("td");
    edit.setAttribute("id","EditTD" + hp.id);
    let remove = document.createElement("td");
    remove.setAttribute("id","DeleteTD" + hp.id);

    id.innerHTML = hp.id;
    brand.innerHTML = hp.brand;
    model.innerHTML = hp.model;
    price.innerHTML = hp.price;
    rating.innerHTML = hp.rating;
    edit.innerHTML = "<button class='editHeadphones btn-warning' value='" + hp.id + "'>Edit</button>"
    remove.innerHTML = "<button class='deleteHeadphones btn-danger' value='" + hp.id + "'>Delete</button>"

    row.appendChild(id);
    row.appendChild(brand);
    row.appendChild(model);
    row.appendChild(price);
    row.appendChild(rating);
    row.appendChild(edit);
    row.appendChild(remove);

    table.appendChild(row);

    editHPListener(document.querySelector("#EditTD" + hp.id + " .editHeadphones"));
    deleteHPListener(document.querySelector("#DeleteTD" + hp.id + " .deleteHeadphones"));
}