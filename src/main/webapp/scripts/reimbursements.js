
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

        row.className = "list-form";

        // create td elements

        let eventtype = document.createElement("td");

        let description = document.createElement("td");

        let location = document.createElement("td");

        let eventtime = document.createElement("td");

        let gradeformat = document.createElement("td");

        let justification = document.createElement("td");

        let missedwork = document.createElement("td");

        let price = document.createElement("td");

        let accepted = document.createElement("td");

        // format dates

        let submityear = reimbursement.submittime.date.year;

        let submitmonth = reimbursement.submittime.date.month;

        let submitday = reimbursement.submittime.date.day;

        let formattedsubmitdate = formatdate(submityear, submitmonth, submitday);

        let year = reimbursement.eventtime.date.year;

        let month = reimbursement.eventtime.date.month;

        let day = reimbursement.eventtime.date.day;

        let formattedeventdate = formatdate(year, month, day);

        let eventDate = new Date(formattedeventdate);

        let submitDate = new Date(formattedsubmitdate);

        // calculate days between event start and request submission

        let daysbetween = Math.round((eventDate-submitDate)/(1000*60*60*24));

        console.log(daysbetween);

        if (daysbetween < 14) {
            
            eventtime.style.color = 'red';

        } 

        // set innerhtml of td elems

        eventtype.innerHTML = reimbursement.eventtype;

        description.innerHTML = reimbursement.description;

        location.innerHTML = reimbursement.location;

        eventtime.innerHTML = formattedeventdate;

        gradeformat.innerHTML = reimbursement.gradeformat;

        justification.innerHTML = reimbursement.justification;

        missedwork.innerHTML = reimbursement.missedwork;

        price.innerHTML = reimbursement.price;

        accepted.innerHTML = reimbursement.approvalstatus;

        // append td elems to row

        row.appendChild(eventtype);

        row.appendChild(description);

        row.appendChild(location);

        row.appendChild(eventtime);

        row.appendChild(gradeformat);

        row.appendChild(justification);

        row.appendChild(missedwork);

        row.appendChild(price);

        row.appendChild(accepted);

        document.getElementById("reimbursementTable").appendChild(row);

    }

}

function formatdate(year, month, day) {

    month = formatDaysAndMonths(month);

    day = formatDaysAndMonths(day);

    return month + "/" + day + "/" + year;


  }

function formatDaysAndMonths(time) {

    if (time < 10) {time = "0" + time};

    return time;

}

window.onload = function () {

    //getReimbursementList();
    
}