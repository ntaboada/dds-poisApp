function SearchService($http) {
    var self = this;
    var userId = localStorage.getItem("UserId");

    this.search = function (callback, filters) {
        var request = {method: 'POST', url: 'http://localhost:8080/api/searchPois', params: {idLoggedUser:userId} , data: filters};
        $http(request).then(callback);
    }
    
}

angular.module("pois-app")
    .factory("SearchService", function($http) {
        return new SearchService($http);
    });

SearchService.$inject = ["$http"];
