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
    if (username === "NatashaB" && password === "brahmbhatt") {         
        location="homepage.xhtml"; 
    } 
    
    if(username.length == 0 || password.length == 0) {
        alert("Invalid UserID or Password");
    } else {
 
    if (username !== "NatashaB"){
        alert("Invalid UserID");
    }
    if (password !== "brahmbhatt"){  
        alert("Invalid Password");
    } 
    }
}
