angular.module('pois-app')
.config(function($stateProvider) {
  return $stateProvider
  .state('main.busqueda', {
    url: "/busqueda",
    templateUrl: "app/modules/pois/views/busquedaWindow.html",
    controller: "BusquedaController",
    controllerAs: "busquedaCtrl"
  })
  .state('main.poi_detail', {
    url: "/busqueda/poi/:id/:name",
    templateUrl: "app/modules/pois/views/poiDetail.html",
    controller: "POIDetailController",
    controllerAs: "poiCtrl",
    resolve: {
      poi: function (POIService, $stateParams) {
        return $stateParams.id;
      },
      poiName: function (POIService, $stateParams) {
        return $stateParams.name;
      }
    }
  }) 
  .state('main.poi_detail.Banco', {
    views : { "servicios-poi": { templateUrl: "app/modules/pois/views/servicios_banco.html" } }
  })
  .state('main.poi_detail.CGP', {
    views : { "servicios-poi": { templateUrl: "app/modules/pois/views/servicios_cgp.html" } }
  })
});