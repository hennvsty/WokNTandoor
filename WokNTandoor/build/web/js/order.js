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
    if(item_id > 7001){
        var bowlSelection = document.getElementById("c" + item_id);
        bowlSelection.innerHTML += bowlSelection;
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
    document.getElementById("orderContainer").style.display = 'none';
    document.getElementById("orderReceipt").style.display = 'block';
    var quantity, item_id, subtotal, orderItems, price;
    var dishesList = ""
    var quantitiesList = "";
    orderItems = document.getElementById("orderItems");
    subtotal = document.getElementById("subtotal");
    subtotal = parseFloat(subtotal.innerHTML).toFixed(2);
    var total = document.getElementById("receiptTotal");
    total.innerHTML = parseFloat(subtotal * 1.0775).toFixed(2);
    for(item_id = 0; item_id <= 7004; item_id++){
        var picPath = document.getElementById("i" + item_id);
        if(picPath === null){
            picPath = "";
        }
        quantity = document.getElementById("q" + item_id);
        price = document.getElementById("p" + item_id);
        if(quantity !== null){
            var quantity_in = parseInt(quantity.innerHTML);
            if(quantity_in > 0){
                if(item_id <= 7001){
                var dishName = document.getElementById("n" + item_id);
                orderItems.innerHTML +=  "<tr><td class=\"menu-item-thumb\"><img src=\""+picPath.src+"\" alt=\"\" style=\"width:100px;height:100px;\"/></td>"
                        +"<td class=\"menu-item-info\"><div class=\"w3-large\">"+dishName.innerHTML+"</div></td>"
                        +"<td class=\"w3-large\"><div class=\"w3-text-white\">"+quantity_in+" X</div></td>"
                        +"<td class=\"menu-item-price\">$<span>"+price.innerHTML+"</span></td>"
                        +"</tr>";
                    dishesList += "%" + dishName.innerHTML + "%";
                    quantitiesList += "%" + quantity_in + "%";
                }
                else{
                    var dishName = document.getElementById("n" + item_id);
                    var selection = document.getElementById("c" + item_id).value;
                    orderItems.innerHTML +=  "<tr><td class=\"menu-item-info\"><div class=\"w3-large w3-padding-4\">"+dishName.innerHTML+"</div></td>"
                        +"<td class=\"w3-large\"><div class=\"w3-text-white\">Flavor: "+selection+"</div></td>"
                        +"<td class=\"w3-large\"><div class=\"w3-text-white\">"+quantity_in+" X</div></td>"
                        +"<td class=\"menu-item-price\">$<span>"+price.innerHTML+"</span></td>"
                        +"</tr>";
                    dishesList += "%" + dishName.innerHTML + "%";
                    quantitiesList += "%" + quantity_in + "%";
                }
            }
        }
        
    }
    
    document.getElementById("total").value = parseFloat(subtotal * 1.0775).toFixed(2);
    document.getElementById("dishOrder").value = dishesList;
    document.getElementById("quantitiesOrder").value = quantitiesList;
}
