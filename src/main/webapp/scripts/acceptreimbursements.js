
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

    xhr.open("GET", "/trms/acceptreimbursement", true);

    xhr.send();

})();

function acceptReimbursement(reimbursementid) {

    event.preventDefault();
    
    let accept = true;

    let reimbursementidint = parseInt(reimbursementid);

    let acceptObj = {reimbursementid: reimbursementidint, accept: accept};

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

        }

    }

    xhr.open("POST", "/trms/acceptreimbursement", true);

    xhr.send(JSON.stringify(acceptObj));

};

function rejectReimbursement(reimbursementid) {

    event.preventDefault();
    
    let accept = false;

    let reimbursementidint = parseInt(reimbursementid);

    let acceptObj = {reimbursementid: reimbursementidint, accept: accept};

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

        }

    }

    xhr.open("POST", "/trms/acceptreimbursement", true);

    xhr.send(JSON.stringify(acceptObj));

};

function displayReimbursementList(reimbursementList){
    
    for(let reimbursement of reimbursementList) {

        let row = document.createElement("tr");

        row.className = "list-form";

        let username = document.createElement("td");

        let description = document.createElement("td");

        let price = document.createElement("td");

        let acceptd = document.createElement("td");

        let rejectd = document.createElement("td");

        let accept = document.createElement("button");

        let reject = document.createElement("button");

        username.innerHTML = reimbursement.username;

        description.innerHTML = reimbursement.description;

        price.innerHTML = reimbursement.price;

        accept.setAttribute("class", "btn btn-success accept");

        //accept.setAttribute("class", "accept");

        accept.setAttribute("name", reimbursement.reimbursementId);

        console.log(reimbursement.reimbursementId);

        reject.setAttribute("class", "btn btn-danger reject");

        //reject.setAttribute("id", "reject");

        reject.setAttribute("name", reimbursement.reimbursementId);

        accept.innerHTML = "ACCEPT";

        reject.innerHTML = "REJECT";

        acceptd.appendChild(accept);

        rejectd.appendChild(reject);

        row.appendChild(username);

        row.appendChild(description);

        row.appendChild(price);

        row.appendChild(acceptd);

        row.appendChild(rejectd);

        document.getElementById("reimbursementTable").appendChild(row);

        $(".accept").on("click", function() {
            console.log("accept clicked");
            acceptReimbursement($(this).attr("name"));
        })

        $(".reject").on("click", function() {
            console.log("reject clicked");
            rejectReimbursement($(this).attr("name"));
        })

    }

}

window.onload = function () {

    $("")
    
}