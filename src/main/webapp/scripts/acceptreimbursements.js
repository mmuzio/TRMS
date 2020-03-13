
class ReimbursementInfo {

    constructor(reimbursementID, username, eventtype, description, location, eventtime, gradeformat, justification, missedwork, price, amount) {

        this.reimbursementID = reimbursementID;

        this.username = username;

        this.eventtype = eventtype;
        this.description = description;
        this.location = location;
        this.eventtime = eventtime;
        this.gradeformat = gradeformat;
        this.justification = justification;
        this.missedwork = missedwork;
        this.price = price;
        this.amount = amount;

    }

}

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

function getEmployeeInfo(username, reimbursementID) {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let employeeInfo = JSON.parse(xhr.responseText);

            console.log(employeeInfo);

            addToModal(employeeInfo, reimbursementID);

        }

    }

    console.log("username is here: " + username);

    console.log("stringified username is here: " + JSON.stringify(username));

    xhr.open("POST", "/trms/employee", true);

    xhr.send(username);

};

function addToModal(employeeInfo, reimbursementID) {

    let email = employeeInfo.email;

    console.log("addToModal reID is " + reimbursementID);

    let reimbursement = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID);
    let username = reimbursement.username; //reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).username;

    let eventtype = reimbursement.eventtype;//reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).eventtype;
    let description = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).description;
    let location = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).location;
    let eventtime = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).eventtime;
    let gradeformat = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).gradeformat;
    let justification = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).justification;
    let missedwork = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).missedwork;
    let price = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).price;
    let amount = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).amount;

    console.log("reimbursement object is \n" + gradeformat);

    let href = "mailto:" + email + "?subject=" + employeeInfo.firstname + " " + employeeInfo.lastname
    + "'s Reimbursement Request" + "&body=Hi " + employeeInfo.firstname + " " + employeeInfo.lastname + ",%0d%0a %0d%0a" + 
    "You recently submitted a reimbursement request with the following information: %0d%0a %0d%0a"
    + "Username: " +  username + "%0d%0a %0d%0a"
    + "Event Type: " +  eventtype + "%0d%0a %0d%0a"
    + "Description: " +  description + "%0d%0a %0d%0a"
    + "Location: " +  location + "%0d%0a %0d%0a"
    + "Event Time: " +  eventtime + "%0d%0a %0d%0a"
    + "Grade Format: " +  gradeformat + "%0d%0a %0d%0a"
    + "Justification: " +  justification + "%0d%0a %0d%0a"
    + "Missed Work: " +  missedwork + "%0d%0a %0d%0a"
    + "Price: " +  price + "%0d%0a %0d%0a"
    + "Amount: " +  amount + "%0d%0a %0d%0a"
    + "Please type your message below this line %0d%0a %0d%0a"; 

    $('#emailanchor').attr("href", href);

    $('#emailp').html(email);

}

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

}

function acceptReimbursement(reimbursementID) {

    event.preventDefault();

    let reimbursementid = parseInt(reimbursementID);

    console.log(reimbursementid);

    let acceptObj = {reimbursementid: reimbursementid};

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

        }

    }

    xhr.open("POST", "/trms/approval", true);

    xhr.send(JSON.stringify(acceptObj));

};

function rejectReimbursement(reimbursementID) {

    event.preventDefault();
    
    let accept = false;

    let reimbursementid = parseInt(reimbursementID);

    console.log(reimbursementid);;

    let acceptObj = {reimbursementid: reimbursementid, accept: accept};

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

        }

    }

    xhr.open("POST", "/trms/acceptreimbursement", true);

    xhr.send(JSON.stringify(acceptObj));

};

var reimbursementInfoObj = [];

function displayReimbursementList(reimbursementList){
    
    for(let reimbursement of reimbursementList) {

        let row = document.createElement("tr");

        row.className = "list-form";

        let username = document.createElement("td");

        let eventtype = document.createElement("td");

        let description = document.createElement("td");

        let location = document.createElement("td");

        let eventtime = document.createElement("td");

        let gradeformat = document.createElement("td");

        let justification = document.createElement("td");

        let missedwork = document.createElement("td");

        let price = document.createElement("td");

        let amount = document.createElement("td");

        let open = document.createElement("td");

        let accepted = document.createElement("td");

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

        let reimbursementInfo = new ReimbursementInfo(reimbursement.reimbursementId, reimbursement.username, reimbursement.eventtype, 
            reimbursement.description, reimbursement.location, formattedeventdate, reimbursement.gradeformat, reimbursement.justification,
            reimbursement.missedwork, reimbursement.price, reimbursement.amount);

        reimbursementInfoObj.push(reimbursementInfo);

        // calculate days between event start and request submission

        let daysbetween = Math.round((eventDate-submitDate)/(1000*60*60*24));

        console.log(daysbetween);

        if (daysbetween < 14) {
            
            eventtime.style.color = 'red';

        } 

        username.innerHTML = reimbursement.username;

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

        console.log(reimbursement.reimbursementId);

        open.appendChild(openbutton);

        row.appendChild(username);

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

            let username = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).username;

            console.log("username is " + username)

            getEmployeeInfo(username, reimbursementID);

            getApprovalInfo(reimbursementID);

            getAttachments(reimbursementID);

            console.log("open button clicked, reimbursementId is " + reimbursementID);

            $('.acceptmod').attr("name", reimbursementID);

            console.log($('.acceptmod').attr("name"));

            $('.rejectmod').attr("name", reimbursementID);

            console.log($('.rejectmod').attr("name"));
            
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

window.onload = function () {

    $(".acceptmod").on("click", function() {

        console.log("accept clicked");

        console.log($(this).attr("name"));

        acceptReimbursement($(this).attr("name"));

    })

    $(".rejectmod").on("click", function() {

        console.log("reject clicked");

        rejectReimbursement($(this).attr("name"));

    })

    $('#exampleModalCenter').on('hidden.bs.modal', function () {
        cleanAttachmentsTable();
      })


      $('#showhideaccepted').click(function(){
        $('td:contains("Accepted")').parent().toggle();
    });
    $('#showhidepending').click(function(){
        $('td:contains("Pending")').parent().toggle();
    });
    
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