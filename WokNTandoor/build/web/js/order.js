/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function openTab(evt, foodMenu) {
    var i, x, tablinks;
    x = document.getElementsByClassName("food-menu");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
    }
    document.getElementById(foodMenu).style.display = "block";
    evt.currentTarget.className += " w3-red";
}

function addSubtotal(item_id) {
    var subtotal, price_in, quantity, quantity_in;

    // Prevents decimal diarrhea
    price_in = parseFloat(document.getElementById("p" + item_id).innerHTML) * 100;
    quantity = document.getElementById("q" + item_id);
    quantity_in = parseInt(quantity.innerHTML);

    if (quantity_in < 3) {
        subtotal = document.getElementById("subtotal");
        subtotal.innerHTML = (parseFloat(subtotal.innerHTML, 10) * 100 + price_in) / 100;
        quantity.innerHTML = quantity_in + 1;
    }
}

function subtractSubtotal(item_id) {
    var subtotal, price_in, quantity, quantity_in;

    price_in = parseFloat(document.getElementById("p" + item_id).innerHTML) * 100;
    quantity = document.getElementById("q" + item_id);
    quantity_in = parseInt(quantity.innerHTML);

    if (quantity_in > 0) {
        subtotal = document.getElementById("subtotal");
        subtotal.innerHTML = (parseFloat(subtotal.innerHTML, 10) * 100 - price_in) / 100;
        quantity.innerHTML = quantity_in - 1;
    }
}
