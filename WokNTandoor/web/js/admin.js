
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

function addDish(){
    document.getElementById("modalAdd").style.display = 'block';
}

function modifyDish(){
    document.getElementById("modalModify").style.display = 'block';
}

function removeDish(){
    document.getElementById("modalRemove").style.display = 'block';
}
