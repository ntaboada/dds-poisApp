function BusquedaController($state, userId, SearchService) {

    if(!localStorage.getItem("UserId") || localStorage.getItem("UserId") == 0) {
        $state.go("main.login");
    }
    
    var self = this;
    self.filters = new Filter();
    self.results = new ResultPOI();
    self.inputFilter;

/*
    this.validateInputFilter = function () {
        return  validatorSearchWindow.isValidFilter(this.inputFilter);
    }
*/
    self.removeFilter = function (filter) {
        self.filters.removeFilter(filter);
    }

    self.pushFilter = function () {
        self.filters.addFilter(self.inputFilter);
        self.inputFilter = "";
    }

    self.executeSearch = function () {
        SearchService.search(function (response) {
             self.results = _.map(response.data, ResultPOI.asResultPOI)
            }, this.filters)
        if(self.results.length=0)
            BootstrapDialog.show({
                message: "Hi"
        });
    }


    self.canExecuteSearch = function () {
        return self.filters.filtersList.length > 0;
    }

};

angular.module("pois-app")
.controller("BusquedaController", BusquedaController);

BusquedaController.$inject = ["$state", "userId", "SearchService"];

