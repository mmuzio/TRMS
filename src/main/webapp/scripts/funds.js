window.onload = function() {

    getInfoForFundsTable();

}

function getInfoForFundsTable() {

    getPendingOrAccepted(true);

    getPendingOrAccepted(false);


}

function getPendingOrAccepted(isPending) {

    let pendingAmount = 0;

    let acceptedAmount = 0;

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
    setTimeout(addToAvailable, 500);

}

function addToAvailable() {

    let x = parseInt(document.getElementById("awardedFunds").innerHTML);

    let y = parseInt(document.getElementById("pendingFunds").innerHTML);

    document.getElementById("availableFunds").innerHTML = 1000 - x - y;

}