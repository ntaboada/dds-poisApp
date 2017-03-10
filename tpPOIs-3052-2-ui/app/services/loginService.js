function LoginService($http) {
    var self = this;
    
    self.login = function (callback, errorCallback, userName, userPassword) {
        var request = {method: 'GET', url: 'http://localhost:8080/api/login', params: {username:userName, password:userPassword} };
        $http(request).success(callback).error(errorCallback);
    };
}

angular.module("pois-app")
    .factory("LoginService", function($http) {
        return new LoginService($http);
    });

LoginService.$inject = ["$http"];
