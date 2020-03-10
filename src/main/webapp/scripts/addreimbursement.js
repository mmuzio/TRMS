class Reimbursement {

    constructor(username, description, price) {

        this.username = username;

        this.description = description;

        this.price = price;

    }

}

function addReimbursement() {

    event.preventDefault();
    
    let username = document.getElementById("username").value;

    let description = document.getElementById("description").value;

    let price = document.getElementById("price").value;

    let reimbursement = new Reimbursement(username, description, price);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

        }

    }

    xhr.open("POST", "/trms/reimbursement", true);
    xhr.send(JSON.stringify(reimbursement));
}

window.onload = function() {
    this.document.getElementById("addReimbursement").addEventListener("click", this.addReimbursement, false);
}