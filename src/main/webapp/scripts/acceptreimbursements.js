
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

function getInfoForFundsTable() {

    getPendingOrAccepted(true);

    getPendingOrAccepted(false);


}

var fundsObj = {pendingAmount: 0, acceptedAmount: 0};

function getPendingOrAccepted(username, isPending) {

    // let pendingAmount = 0;

    // let acceptedAmount = 0;

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log(xhr.responseText);

            let fundsAmount = JSON.parse(xhr.responseText);

            console.log(fundsAmount);

            if (isPending) {

                pendingAmount = parseInt(fundsAmount);

                fundsObj.pendingAmount = pendingAmount;
 
            } else {

                acceptedAmount = parseInt(fundsAmount);

                fundsObj.acceptedAmount = acceptedAmount;

            }

        }

    }

    xhr.open("GET", "/trms/funds?username=" + username + "&isPending=" + isPending, true);

    xhr.send();

};

function getAvailableFunds(username) {

    getPendingOrAccepted(username, true);

    getPendingOrAccepted(username, false);

    return 1000 - fundsObj.pendingAmount - fundsObj.acceptedAmount;

}

function addWarningMessage() {

    let warning = document.createElement("tr");

    let warningtd = document.createElement("td");

    warning.setAttribute("class", "text-center");

    warningtd.setAttribute("colspan", "3");

    warningtd.innerHTML = "Reimbursement amount exceeds available funds!";

    warning.style.border = '3px solid red';

    warning.appendChild(warningtd);

    document.getElementById("infoTable").appendChild(warning);

}

function addToModal(employeeInfo, reimbursementID) {

    let i = 1;

    let infoTable = document.getElementById("infoTable");

    infoTable.innerHTML = `<tr class="text-center">
    <th colspan="3" style="font-size: 36px;">Employee Information</th>
  </tr>
    <tr id="infoHeaderRow" class="text-center">
        <th style="width: 33.33%" id="nameHeader">Employee Name</th>
        <th style="width: 33.33%" id="fundsHeader">Available Funds</th>
        <th style="width: 33.33%"id="amountHeader">Reimbursement Amount</th>
    </tr>
    <tr id="infoRow" class="text-center list-form">
      <td id="nameid"></td>
      <td id="fundsid"></td>
      <td id="amountid"></td>
    </tr>`;

    let employee1 = employeeInfo[0];

    let name = employee1.firstname + " " + employee1.lastname;

    let amount = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).amount;

    let availableamount = getAvailableFunds(employee1.username);

    document.getElementById("nameid").innerHTML = name;

    document.getElementById("fundsid").innerHTML = availableamount;

    document.getElementById("amountid").innerHTML = amount;

    if (amount > availableamount) {

        addWarningMessage();

    }

    $("#addemailhere").empty();

    console.log("emails should empty");

    for(let employee of employeeInfo) {

        let email = employee.email;

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

        let greeting = "";

        if (i == 1) {

            greeting = "You recently submitted a reimbursement request with the following information: %0d%0a %0d%0a";

        } else {

            greeting = "I would like some more info about the following reimbursement request: %0d%0a %0d%0a";

        }

        let href = "mailto:" + email + "?subject=" + employee1.firstname + " " + employee1.lastname
        + "'s Reimbursement Request" + "&body=Hi " + employee.firstname + " " + employee.lastname + ",%0d%0a %0d%0a" 
        + greeting
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

        createEmailp(i, href, email);

        i += 1;

    }

}

function createEmailp(i, href, email) {

    let employeeType = 'Employee';

    if (i == 1) {

        employeeType = 'Employee';

    } else if (i == 2) {

        employeeType = 'Supervisor';

    } else {

        employeeType = 'Dep Head';

    }

    let emaildiv = document.createElement("div");
    emaildiv.setAttribute("id", "emaildiv" + i);
    emaildiv.setAttribute("class", "col");
    emaildiv.innerHTML = `<a id="emailanchor${i}" href="${href}">
    <h5>${employeeType}</h5>
    <i class="fa fa-envelope fa-3x"></i>
    <h3 class="d-none d-lg-block d-xl-block">E-mail</h3>
    <p class="d-none d-lg-block d-xl-block" id="emailp${i}">${email}</p>
    <br>
    </a>`;

    document.getElementById("addemailhere").appendChild(emaildiv);

}

function addAttachmentsToModal(attachmentList) {

    cleanAttachmentsTable();

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

            $("#forthumb").html('<div class="col"><i class="fa fa-thumbs-up fa-3x"></i></div>')


            document.getElementById("infospan").innerHTML = "Reimbursement Accepted";

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

            $("#forthumb").html('<div class="col"><i class="fa fa-thumbs-down fa-3x"></i></div>')

            document.getElementById("infospan").innerHTML = "Reimbursement Rejected";

        }

    }

    xhr.open("POST", "/trms/acceptreimbursement", true);

    xhr.send(JSON.stringify(acceptObj));

};

var reimbursementInfoObj = [];

function displayReimbursementList(reimbursementList){
    
    for(let reimbursement of reimbursementList) {

        let row = document.createElement("tr");

        row.setAttribute("data-toggle", "modal");

        row.setAttribute("data-target", "#exampleModalCenter");

        row.setAttribute("name", reimbursement.reimbursementId);

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

        let reimbursementInfo = new ReimbursementInfo(reimbursement.reimbursementId, reimbursement.username, reimbursement.eventtype, 
            reimbursement.description, reimbursement.location, formattedeventdate, reimbursement.gradeformat, reimbursement.justification,
            reimbursement.missedwork, reimbursement.price, reimbursement.amount);

        reimbursementInfoObj.push(reimbursementInfo);

        // calculate days between event start and request submission

        let daysbetween = Math.round((eventDate-submitDate)/(1000*60*60*24));

        if (daysbetween < 14) {
            
            row.style.border = '3px solid red';

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

        document.getElementById("reimbursementTable").appendChild(row);

        $("tr").on("click", function() {

            reimbursementID = $(this).attr("name");

            let username = reimbursementInfoObj.find(re => re.reimbursementID == reimbursementID).username;

            getEmployeeInfo(username, reimbursementID);

            getApprovalInfo(reimbursementID);

            getAttachments(reimbursementID);

            $('.acceptmod').attr("name", reimbursementID);

            $('.rejectmod').attr("name", reimbursementID);
            
        });

    }

    let row = document.createElement("tr");

    let td = document.createElement("td");

    td.setAttribute("colspan", "11");

    td.innerHTML = "Urgent requests are bordered in red";

    row.appendChild(td);

    document.getElementById("reimbursementTable").appendChild(row);

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

    var acceptedclicked = 0;
    // show/hide button for accepted reimbursements
    $('#showhideaccepted').click(function(){
        $('td:contains("Accepted")').parent().toggle();
        if (acceptedclicked == 0){
            $('#showhideaccepted').html("Show Accepted");
            acceptedclicked = 1;
         } else{
            $('#showhideaccepted').html("Hide Accepted");
            acceptedclicked = 0;
         }
    });

    var pendingclicked = 0; 
    // show/hide button for pending reimbursements
    $('#showhidepending').click(function(){
        $('td:contains("Pending")').parent().toggle();
        if (pendingclicked == 0){
            $('#showhidepending').html("Show Pending");
            pendingclicked = 1;
         } else{
            $('#showhidepending').html("Hide Pending");
            pendingclicked = 0;
         }
    });

    var rejectedclicked = 0;
    // show/hide button for rejected reimbursements
    $('#showhiderejected').click(function(){
        $('td:contains("Rejected")').parent().toggle();
        if (acceptedclicked == 0){
            $('#showhiderejected').html("Show Rejected");
            acceptedclicked = 1;
         } else{
            $('#showhiderejected').html("Hide Rejected");
            acceptedclicked = 0;
         }
    });
    
}

function cleanAttachmentsTable() {

    //empty the table
    $("#attachmentTable").empty();

    $("infoTable").empty();

    $("#forthumb").empty();

    $("#infospan").empty();

    //create table title  
    let row1 = document.createElement("tr");
    row1.setAttribute("class", "text-center");
    let header1 = document.createElement("th");
    header1.setAttribute("colspan", "3");
    header1.setAttribute("style", "font-size: 36px;");
    header1.innerHTML = "Attachments";
    row1.appendChild(header1);

    //create table headers
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

    //repopulate table with title and headers
    let attachmentTable = document.getElementById("attachmentTable");
    attachmentTable.appendChild(row1);
    attachmentTable.appendChild(row2);

}