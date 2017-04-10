(function() {
  $(function() {
    $(".login--container").removeClass("preload");
    this.timer = window.setTimeout((function(_this) {
      return function() {
        return $(".login--container").toggleClass("login--active");
      };
    })(this), 3000);

  });

}).call(this);

$(document).ready(function(){
    $("#login").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        
        if (username == '' || password == ''){
            alert("Fields cannot be blank");
        } else {
            if (username == "NatashaB") {
                if (password == "brahmbhatt") {
                    window.location.replace("homepage.xhtml");
                } else {
                    alert("Invalid Password");
                }
            } else {
                alert("Invalid UserID");
            }
        }
    });
});

function pasuser(form) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username === "NatashaB") { 
    if (password === "brahmbhatt") {              
        location="homepage.xhtml" 
    } else {
        alert("Invalid Password")
    }
    } else {  
        alert("Invalid UserID")
    }
}
//-->