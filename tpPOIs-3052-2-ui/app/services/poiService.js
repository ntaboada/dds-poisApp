function POIService($http) {
    var self = this;
    var userId = localStorage.getItem("UserId");
    
    self.getPoiDetail = function (callback, errorCallback, poiId) {
        var request = {method: 'GET', url: 'http://localhost:8080/api/poiDetails/' + poiId, params: {idLoggedUser:userId} };
        $http(request).success(callback).error(errorCallback);
    };
    
    self.getPoiDetailFromPOIName = function (callback, errorCallback, poiName) {
        var parameter = JSON.stringify(
            {
                "poiName": poiName,
                "idLoggedUser":userId
            });
        var request = {method: 'POST', url: 'http://localhost:8080/api/poiDetails/', data: parameter };
        $http(request).success(callback).error(errorCallback);
    };

    self.sendReview = function(callback, errorCallback, idPoi, userQualification, userComment) {
    	var parameter = JSON.stringify(
    		{
    			"idUser": userId ,
    			"comment": userComment,
    			"puntaje": userQualification
    		});
        var request = {method: 'POST', url: 'http://localhost:8080/api/poiDetails/review', params: {idPoi:idPoi} , data: parameter};
        $http(request).success(callback).error(errorCallback);
    };

    self.sendReviewForPOIByName = function(callback, errorCallback, poiName, userQualification, userComment) {
        var parameter = JSON.stringify(
            {
                "poiName" : poiName,
                "idUser": userId ,
                "comment": userComment,
                "puntaje": parseInt(userQualification)
            });
        var request = {method: 'POST', url: 'http://localhost:8080/api/poiDetails/reviewByName/', data: parameter};
        $http(request).success(callback).error(errorCallback);
    };

    self.saveFavorite = function(successCallback, errorCallback, idPoi) {
        var request = {method: 'POST', url: 'http://localhost:8080/api/favoritePOI', params: {idPoi:idPoi, idLoggedUser:userId}};
        $http(request).success(successCallback).error(errorCallback);
    };

    self.removeFavorite = function(successCallback, errorCallback, idPoi) {
        var request = {method: 'DELETE', url: 'http://localhost:8080/api/favoritePOI', params: {idPoi:idPoi, idLoggedUser:userId}};
        $http(request).success(successCallback).error(errorCallback);
    };
}

angular.module("pois-app")
    .factory("POIService", function($http) {
        return new POIService($http);
    });

POIService.$inject = ["$http"];
