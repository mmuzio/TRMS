(function getReimbursementList() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let reimbursementList = JSON.parse(xhr.responseText);

            console.log(reimbursementList);

            displayReimbursementList(reimbursementList);

        }

    }

    xhr.open("GET", "/trms/reimbursement", true);

    xhr.send();

})();

function displayReimbursementList(reimbursementList){
    
    for(let reimbursement of reimbursementList) {

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

        document.getElementById("reimbursementTable").appendChild(row);

    }

}

window.onload = function () {

    //getReimbursementList();
    
}