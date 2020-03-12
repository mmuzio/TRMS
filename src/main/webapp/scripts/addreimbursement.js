class Reimbursement {

    constructor(eventtype, description, location, eventtimestring, gradeformat, justification, missedwork, price) {

        this.eventtype = eventtype;

        this.description = description;

        this.location = location;

        this.eventtimestring = eventtimestring;

        this.gradeformat = gradeformat;

        this.justification = justification;

        this.missedwork = missedwork;

        this.price = price;

    }

}

function calculateProjectedReimbursement() {

    let eventtype = document.getElementById("eventtype").value;

    let price = document.getElementById("price").value;

    console.log("price is " + price);

    console.log("eventtype is " + eventtype); 

    let projectedReimbursementPercentages = {"University Course": 0.7, "Seminar": 0.6, "Certification Prep Class": 0.75, "Certification": 1, "Technical Training": 0.9, "Other": 0.3};

    let reimbursementPercentage = projectedReimbursementPercentages[eventtype];

    console.log("percent is " + reimbursementPercentage);

    let amountval = price * reimbursementPercentage;

    console.log("amount is " + amountval);

    document.getElementById("amount").value = amountval.toFixed(2);

}

function addReimbursement() {

    event.preventDefault();

    let eventtype = document.getElementById("eventtype").value;

    console.log("eventtype is " + eventtype);

    let description = document.getElementById("description").value;

    let location = document.getElementById("location").value;

    let eventtimestring = document.getElementById("eventtimestring").value;

    let gradeformat = document.getElementById("gradeformat").value;

    let justification = document.getElementById("justification").value;

    let missedwork = document.getElementById("missedwork").value;

    let price = document.getElementById("price").value;

    let reimbursement = new Reimbursement(eventtype, description, location, eventtimestring, gradeformat, justification, missedwork, price);

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

    document.getElementById("addReimbursement").onclick = function () {

        location.href = "reimbursements.html";

    };

    let eventtype = document.getElementById("eventtype");

    let price = document.getElementById("price");

    eventtype.addEventListener("change", calculateProjectedReimbursement);

    price.addEventListener("change", calculateProjectedReimbursement);

}