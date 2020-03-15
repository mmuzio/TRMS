//this file is not used because using it caused errors
//instead I just added all of the code into the reimbursement.js
//and that worked fine

window.onload = function() {

    $('#exampleModalCenter').on('shown.bs.modal', function () {

        let reimbursementID = $('.savechanges').attr("name");

        getApprovalInfo(reimbursementID);
        // do something…
    })

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