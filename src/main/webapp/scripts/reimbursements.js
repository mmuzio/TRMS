// class ReimbursementInfo {

//     constructor(reimbursementID, username, eventtype, description, location, eventtime, gradeformat, justification, missedwork, price, amount) {

//         this.reimbursementID = reimbursementID;

//         this.username = username;

//         this.eventtype = eventtype;
//         this.description = description;
//         this.location = location;
//         this.eventtime = eventtime;
//         this.gradeformat = gradeformat;
//         this.justification = justification;
//         this.missedwork = missedwork;
//         this.price = price;
//         this.amount = amount;

//     }

// }

class Attachment {

    constructor(reimbursementid, attachmenttype, description, attachmentname, attachmentlink) {

        this.reimbursementid = reimbursementid;

        this.attachmenttype = attachmenttype;

        this.description = description;

        this.attachmentname = attachmentname;

        this.attachmentlink = attachmentlink;

    }

}


function getReimbursementList() {

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

};

//var username = "";

function displayReimbursementList(reimbursementList){

    let i = 0;
    
    for(let reimbursement of reimbursementList) {

        //username = reimbursement.username;

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

        let amount = document.createElement("td");

        amount.setAttribute("name", i);

        let accepted = document.createElement("td");

        accepted.setAttribute("name", i);

        let open = document.createElement("td");

        let openbutton = document.createElement("button");

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

        amount.innerHTML = reimbursement.amount;

        accepted.innerHTML = reimbursement.approvalstatus;

        openbutton.setAttribute("class", "btn btn-primary open");

        openbutton.setAttribute("data-toggle", "modal");

        openbutton.setAttribute("data-target", "#exampleModalCenter");

        openbutton.setAttribute("name", reimbursement.reimbursementId);

        openbutton.innerHTML = "OPEN";

        open.appendChild(openbutton);

        // append td elems to row

        row.appendChild(eventtype);

        row.appendChild(description);

        row.appendChild(location);

        row.appendChild(eventtime);

        row.appendChild(gradeformat);

        row.appendChild(justification);

        row.appendChild(missedwork);

        row.appendChild(price);

        row.appendChild(amount);

        row.appendChild(accepted);

        row.appendChild(open);

        document.getElementById("reimbursementTable").appendChild(row);

        $(".open").on("click", function() {

            reimbursementID = $(this).attr("name");

            getApprovalInfo(reimbursementID);

            getAttachments(reimbursementID);    

            console.log("open button clicked, reimbursementId is " + reimbursementID);

            $('.savechanges').attr("name", reimbursementID);
            
        })

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

function getApprovalInfo(reimbursementID) {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let approvalInfo = JSON.parse(xhr.responseText);

            console.log(approvalInfo);

            addApprovalInfoToModal(approvalInfo);

        }

    }

    xhr.open("GET", "/trms/approval?reimbursementid=" + reimbursementID, true);

    xhr.send();

};

function getAttachments(reimbursementID) {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let attachmentList = JSON.parse(xhr.responseText);

            console.log(attachmentList);

            addAttachmentsToModal(attachmentList);

        }

    }

    xhr.open("GET", "/trms/attachment?reimbursementid=" + reimbursementID, true);

    xhr.send();

};

function addAttachmentsToModal(attachmentList) {

    for(let attachment of attachmentList) {

        let row = document.createElement("tr");

        row.className = "list-form text-center";

        // create td elements

        let attachmenttype = document.createElement("td");

        let description = document.createElement("td");

        let attachmentlink = document.createElement("td");

        let attachmentlinkanchor = document.createElement("a");

        attachmentlinkanchor.setAttribute("href", attachment.attachmentlink);

        attachmentlinkanchor.setAttribute("target", "_blank");

        attachmentlinkanchor.setAttribute("text-decoration", "none !important");

        attachmenttype.innerHTML = attachment.attachmenttype;

        description.innerHTML = attachment.description;

        attachmentlinkanchor.innerHTML = attachment.attachmentname;

        attachmentlink.appendChild(attachmentlinkanchor);

        row.appendChild(attachmenttype);

        row.appendChild(description);

        row.appendChild(attachmentlink);

        document.getElementById("attachmentTable").appendChild(row);

    }

};

function acceptedOrPending(status) {
    if (status == true) {
        return "Accepted";
    } else {
        return "Pending";
    }
}

function addApprovalInfoToModal(approvalInfo) {

    let supervisorAccepted = approvalInfo.supervisorAccepted;

    let headAccepted = approvalInfo.headAccepted;

    let bencoAccepted = approvalInfo.bencoAccepted;

    let supervisorAcceptedtd = document.getElementById("supervisorid");

    let headAcceptedtd = document.getElementById("headid");

    let bencoAcceptedtd = document.getElementById("bencoid");

    supervisorAcceptedtd.innerHTML = acceptedOrPending(supervisorAccepted);

    headAcceptedtd.innerHTML = acceptedOrPending(headAccepted);

    bencoAcceptedtd.innerHTML = acceptedOrPending(bencoAccepted);

}

function addAttachment(reimbursementID) {

    event.preventDefault();

    let reimbursementid = parseInt(reimbursementID);

    console.log(reimbursementID);

    console.log(reimbursementid);

    let attachmenttype = document.getElementById("attachmenttype").value;

    let description = document.getElementById("descriptiona").value;

    let attachmentname = document.getElementById("attachmentname").value;

    let attachmentlink = document.getElementById("attachmentlink").value;

    let attachment = new Attachment(reimbursementid, attachmenttype, description, attachmentname, attachmentlink);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

        }

    }

    xhr.open("POST", "/trms/attachment", true);

    xhr.send(JSON.stringify(attachment));

}

var pendingAmount = 0;

var acceptedAmount = 0;

function getPendingOrAccepted(isPending) {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let fundsAmount = JSON.parse(xhr.responseText);

            console.log(fundsAmount);

            if (isPending) {

                pendingAmount = fundsAmount;
                addToPending(pendingAmount);
 
            } else {

                acceptedAmount = fundsAmount;
                addToAccepted(acceptedAmount);

            }

        }

    }

    xhr.open("GET", "/trms/funds?isPending=" + isPending, true);

    xhr.send();

};

function addToPending(fundsAmount) {

    document.getElementById("pendingFunds").innerHTML = fundsAmount;

}

function addToAccepted(fundsAmount) {

    document.getElementById("awardedFunds").innerHTML = fundsAmount;
    setTimeout(addToAvailable(), 3000);

}

function addToAvailable() {

    document.getElementById("availableFunds").innerHTML = 1000 - acceptedAmount - pendingAmount;

}

window.onload = function () {

    getReimbursementList();

    $(".savechanges").on("click", function() {

        console.log("save clicked");

        let name = $(this).attr("name");

        console.log(name);

        addAttachment($(this).attr("name"));

    });

    $('#exampleModalCenter').on('hidden.bs.modal', function () {
        cleanAttachmentsTable();
      });

    

    // $("#seefunds").on("click", function() {

    //     let x = getPendingFunds();
    //     let y = getAwardedFunds();
    //     setAvailableFunds(x, y);
    
    // });

    getInfoForFundsTable();
    
}

function getInfoForFundsTable() {

    getPendingOrAccepted(true);

    getPendingOrAccepted(false);


}

function cleanAttachmentsTable() {
    $("#attachmentTable").empty();

    let row1 = document.createElement("tr");
    row1.setAttribute("class", "text-center");
    let header1 = document.createElement("th");
    header1.setAttribute("colspan", "3");
    header1.setAttribute("style", "font-size: 36px;");
    header1.innerHTML = "Attachments";
    row1.appendChild(header1);

    let row2 = document.createElement("tr");
    row2.setAttribute("class", "text-center");
    let header2 = document.createElement("th");
    header2.setAttribute("style", "width: 33.3%;");
    header2.innerHTML = "Type";
    let header3 = document.createElement("th");
    header3.setAttribute("style", "width: 33.3%;");
    header3.innerHTML = "Description";
    let header4 = document.createElement("th");
    header4.setAttribute("style", "width: 33.3%;");
    header4.innerHTML = "Link";
    row2.appendChild(header2);
    row2.appendChild(header3);
    row2.appendChild(header4);

    let attachmentTable = document.getElementById("attachmentTable");
    attachmentTable.appendChild(row1);
    attachmentTable.appendChild(row2);

}

// function setAvailableFunds(x, y) {

//     //let available = 1000 - getPendingFunds() - getAwardedFunds();

//     let available = 1000 - x - y;

//     document.getElementById("availableFunds").innerHTML = available;

// }

// function getPendingFunds() {
//     output = 0;
//     $("#reimbursementTable td:contains('Pending')").prev().each(function () {
//         output += parseInt(this.innerHTML); // "this" is the current element in the loop
//     });
//     console.log("Pending = " + output);
//     document.getElementById("pendingFunds").innerHTML = output;
//     return output;

// }

// function getAwardedFunds() {
//     output = 0;
//     $("#reimbursementTable td:contains('Accepted')").prev().each(function () {
//         output += parseInt(this.innerHTML); // "this" is the current element in the loop
//     });
//     console.log("Awarded = " + output);
//     document.getElementById("awardedFunds").innerHTML = output;
//     return output;

// }