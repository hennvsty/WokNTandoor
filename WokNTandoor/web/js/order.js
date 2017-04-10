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

function addSubtotal(item_id, cat_id) {
    var subtotal, price_in, quantity, quantity_in, cat_subtotal;
    // Prevents decimal diarrhea
    cat_subtotal = document.getElementById(cat_id);
    cat_subtotal.style.display = "block";
    price_in = parseFloat(document.getElementById("p" + item_id).innerHTML);
    cat_subtotal.innerHTML = (parseFloat(cat_subtotal.innerHTML) + price_in).toFixed(2);
    quantity = document.getElementById("q" + item_id);
    quantity_in = parseInt(quantity.innerHTML);
    subtotal = document.getElementById("subtotal");
    subtotal.innerHTML = (parseFloat(subtotal.innerHTML) + price_in).toFixed(2);
    quantity.innerHTML = quantity_in + 1;
    var x = document.getElementById("orderButton");
    if (x.style.display == 'none') {
        x.style.display = 'inline';
    }
}

function subtractSubtotal(item_id, cat_id) {
    var subtotal, price_in, quantity, quantity_in, cat_subtotal;
    price_in = parseFloat(document.getElementById("p" + item_id).innerHTML);
    quantity = document.getElementById("q" + item_id);
    cat_subtotal = document.getElementById(cat_id);
    quantity_in = parseInt(quantity.innerHTML);
    if (quantity_in > 0) {
        if(parseFloat(cat_subtotal.innerHTML) > 0){
            cat_subtotal.innerHTML = (parseFloat(cat_subtotal.innerHTML) - price_in).toFixed(2);
        }
        subtotal = document.getElementById("subtotal");
        subtotal.innerHTML = (parseFloat(subtotal.innerHTML) - price_in).toFixed(2);
        quantity.innerHTML = quantity_in - 1;
    }
    if(parseFloat(cat_subtotal.innerHTML) <= 0){
            cat_subtotal.style.display = "none";
    }
    var x = document.getElementById("orderButton");
    if (parseFloat(subtotal.innerHTML) === 0) {
        x.style.display = 'none';
    }
}

function toggle(table_id){
    var x = document.getElementById(table_id);
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}
function placeOrder(){
    var quantity, item_id, subtotal;
    item_id = 0;
    subtotal = document.getElementById("subtotal");
    subtotal = parseFloat(subtotal.innerHTML).toFixed(2);
    quantity = document.getElementById("q" + item_id);
       
}
