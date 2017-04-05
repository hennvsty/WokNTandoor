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
if (form.id.value=="NatashaB") { 
if (form.pass.value=="brahmbhatt") {              
location="homepage.xhtml" 
} else {
alert("Invalid Password")
}
} else {  alert("Invalid UserID")
}
}
//-->