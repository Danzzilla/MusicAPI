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

document.querySelector("#add-music").addEventListener("click", event => {
    let card = document.getElementById("music-card");

    if(card.classList.contains("d-none")){
        card.classList.remove("d-none");
    }else{
        card.classList.add("d-none");
    }
})

document.querySelector("#add-headphones").addEventListener("click", event => {
    let card = document.getElementById("headphones-card");

    if(card.classList.contains("d-none")){
        card.classList.remove("d-none");
    }else{
        card.classList.add("d-none");
    }
})