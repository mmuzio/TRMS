class Car {
    constructor(make, model, year, vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }
}

function addCar() {
    event.preventDefault();
    
    let make = document.getElementById("make").value;
    let model = document.getElementById("model").value;
    let year = document.getElementById("year").value;
    let vin = document.getElementById("vin").value;
    let car = new Car(make, model, year, vin);

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("Success");
        }
    }
    xhr.open("POST", "/CarDealership/car", true);
    xhr.send(JSON.stringify(car));
}

window.onload = function() {
    this.document.getElementById("addCar").addEventListener("click", addCar, false);
}