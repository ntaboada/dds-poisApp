function LoginCtrl($state, userId, LoginService) {
  var self = this;
  self.userName = "";
  self.userPassword = "";

  self.login = function login() {
  	LoginService.login(
  		function successCallback(response) {
        localStorage.setItem("UserId", response);
        $state.go("main.busqueda");
  		}, 
  		function errorCallback(data) {
  			 alert("Ocurrió un error al autenticar el usuario");
  		}, self.userName, self.userPassword);
  };

  if(localStorage.getItem("UserId") && localStorage.getItem("UserId") != 0) {
      $state.go("main.busqueda");
  }

};

angular.module("pois-app")
.controller("LoginCtrl", LoginCtrl);

LoginCtrl.$inject = [ "$state", "userId", "LoginService"];