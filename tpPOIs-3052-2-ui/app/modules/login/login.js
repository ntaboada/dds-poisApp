angular.module('pois-app')
.config(function($stateProvider) {
  return $stateProvider
  .state('main.login', {
    url: "/",
    templateUrl: "app/modules/login/views/login.html",
    controller: "LoginCtrl",
    controllerAs: "loginCtrl"
  });
});