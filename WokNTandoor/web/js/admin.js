
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

function displayAddModal(){
    document.getElementById("addForm:addName").value = "";
    document.getElementById("addForm:addPrice").value = "0.0";
    document.getElementById("addForm:addDescription").value = "";
    document.getElementById("addModal").style.display = 'block';
}

function hideAddModal() {
    document.getElementById("addForm:addName").value = "";
    document.getElementById("addForm:addPrice").value = "0.0";
    document.getElementById("addForm:addDescription").value = "";
    document.getElementById("addModal").style.display = 'none';
}

function displayEditModal(id){
    var name = document.getElementById("n" + id).innerHTML.toString();
    var price = document.getElementById("p" + id).innerHTML.toString();
    var description = document.getElementById("d" + id).innerHTML.toString();
    
    document.getElementById("editForm:editName").value = name;
    document.getElementById("editForm:editPrice").value = price;
    document.getElementById("editForm:editDescription").value = description;
    
    document.getElementById("editId").value = id.toString();
    
    document.getElementById("editModal").style.display = 'block';
}

function hideEditModal() {
    document.getElementById("editModal").style.display = 'none';
}

function editDish() {
    var id = document.getElementById("editId").value;
    var name = document.getElementById("n" + id).innerHTML.toString();
    var price = document.getElementById("p" + id).innerHTML.toString();
    var description = document.getElementById("d" + id).innerHTML.toString();
    
    //getInputAction([ { name: 'name', value: name }, { name: 'price', value: price }, { name: 'description', value: description } ]);
    
    editAction([ { name: 'name', value: name } ]);
    
    hideEditModal();
}

function displayRemove(){
    document.getElementById("modalRemove").style.display = 'block';
}

function removeDish(id) {
    var name = document.getElementById("n" + id).innerHTML.toString();
    removeAction([ { name: 'name', value: name } ]);
}

