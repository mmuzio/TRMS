
window.onload = function() {

    $(".savechanges").on("click", function() {

        console.log("save clicked");

        let name = $(this).attr("name");

        console.log(name);

        addAttachment($(this).attr("name"));

    });

    $('#exampleModalCenter').on('hidden.bs.modal', function () {
        cleanAttachmentsTable();
      });

    $(".open").on("click", function() {

        reimbursementID = $(this).attr("name");

        getAttachments(reimbursementID);    

        console.log("open button clicked, reimbursementId is " + reimbursementID);

        $('.savechanges').attr("name", reimbursementID);
        
    })

}

class Attachment {

    constructor(reimbursementid, attachmenttype, description, attachmentname, attachmentlink) {

        this.reimbursementid = reimbursementid;

        this.attachmenttype = attachmenttype;

        this.description = description;

        this.attachmentname = attachmentname;

        this.attachmentlink = attachmentlink;

    }

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

function cleanAttachmentsTable() {

    // empty the table
    $("#attachmentTable").empty();

    // repopulate table with headers
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