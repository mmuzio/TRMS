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

            let reimbursementList = JSON.parse(xhr.responseText);

            displayReimbursementList(reimbursementList);

        }

    }

    xhr.open("GET", "/trms/reimbursement", true);

    xhr.send();

};

function displayReimbursementList(reimbursementList){

    let i = 0;
    
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

            $('.savechanges').attr("name", reimbursementID);
    
            getAttachments(reimbursementID);
            
        });

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

};

function addAttachment(reimbursementID) {

    event.preventDefault();

    let reimbursementid = parseInt(reimbursementID);

    let attachmenttype = document.getElementById("attachmenttype").value;

    let description = document.getElementById("descriptiona").value;

    let attachmentname = document.getElementById("attachmentname").value;

    let attachmentlink = document.getElementById("attachmentlink").value;

    let attachment = new Attachment(reimbursementid, attachmenttype, description, attachmentname, attachmentlink);

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {

        if (xhr.readyState === 4 && xhr.status === 200) {

            console.log("Success");

            document.getElementById("attachmentspan").innerHTML = "Attachment Added";

        }

    }

    xhr.open("POST", "/trms/attachment", true);

    xhr.send(JSON.stringify(attachment));

}

window.onload = function () {

    getReimbursementList();

    $(".savechanges").on("click", function() {

        let name = $(this).attr("name");

        // TO-DO add success message

        addAttachment($(this).attr("name"));

    });

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
    
    console.log("cleaning attachment table");

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


// approvals
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