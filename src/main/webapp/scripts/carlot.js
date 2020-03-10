function getCarList() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let carList = JSON.parse(xhr.responseText);
            console.log(carList);
            displayCarList(carList);
        }
    }
    xhr.open("GET", "/CarDealership/car", true);
    xhr.send();
}

function displayCarList(carList){
    
    for(let car of carList) {
        let row = document.createElement("tr");
        row.className = "list-form"
        let make = document.createElement("td");
        let model = document.createElement("td");
        let year = document.createElement("td");
        let vin = document.createElement("td");
        make.innerHTML = car.make;
        model.innerHTML = car.model;
        year.innerHTML = car.year;
        vin.innerHTML = car.vin;
        row.appendChild(make);
        row.appendChild(model);
        row.appendChild(year);
        row.appendChild(vin);
        document.getElementById("carTable").appendChild(row);
    }
}

window.onload = function () {
    this.getCarList();
}