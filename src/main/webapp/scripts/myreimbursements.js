(function getMyReimbursementList() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let myReimbursementList = JSON.parse(xhr.responseText);

            console.log(myReimbursementList);

            displayMyReimbursementList(myReimbursementList);

        }

    }

    xhr.open("GET", "/trms/reimbursement", true);

    xhr.send();

})();

function displayMyReimbursementList(myReimbursementList){
    
    for(let reimbursement of myReimbursementList) {

        let row = document.createElement("tr");

        row.className = "list-form"

        let username = document.createElement("td");

        let description = document.createElement("td");

        let price = document.createElement("td");

        username.innerHTML = reimbursement.username;

        description.innerHTML = reimbursement.description;

        price.innerHTML = reimbursement.price;

        row.appendChild(username);

        row.appendChild(description);

        row.appendChild(price);

        document.getElementById("myReimbursementTable").appendChild(row);

    }

}

window.onload = function () {

    //getReimbursementList();
    
}