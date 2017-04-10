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