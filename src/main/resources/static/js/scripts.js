// $(document).ready(function () {
//     $('#music-records').DataTable({
//         "ordering": false
//     });
//     $('.dataTables_length').addClass('bs-select');
// });
//
// $(document).ready(function () {
//     $('#headphones-records').DataTable({
//         "ordering": false
//     });
//     $('.dataTables_length').addClass('bs-select');
// });

//Initiators

load("music");
load("headphones");

document.querySelector("#submit-music").addEventListener("click", () => {
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

document.querySelector("#submit-headphones").addEventListener("click", () => {
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

document.querySelector("#add-music").addEventListener("click", () => {
    let card = document.getElementById("music-card");

    if(card.classList.contains("d-none")){
        card.classList.remove("d-none");
    }else{
        card.classList.add("d-none");
    }
})

document.querySelector("#add-headphones").addEventListener("click", () => {
    let card = document.getElementById("headphones-card");

    if(card.classList.contains("d-none")){
        card.classList.remove("d-none");
    }else{
        card.classList.add("d-none");
    }
})