
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

function displayAdd(){
    document.getElementById("modalAdd").style.display = 'block';
}

function displayEdit(){
    document.getElementById("modalEdit").style.display = 'block';
}

function displayRemove(){
    document.getElementById("modalRemove").style.display = 'block';
}

function removeDish(id) {
    var name = document.getElementById("n" + id).innerHTML.toString();
    removeAction([ { name: 'name', value: name } ]);
}
