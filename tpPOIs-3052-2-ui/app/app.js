'use strict';

angular.module('pois-app', ['ui.router'])
.config(function($urlRouterProvider, $locationProvider) {
  $urlRouterProvider.otherwise('/');
  return $locationProvider.html5Mode(true);
})
.constant('_', window._)
.value('userId', {})
.config(function($stateProvider) {
  return $stateProvider
  .state('main', {
    abstract: true,
    views: {
      'layout': {
        templateUrl: "app/layout/layout.html"
      },
      'container@main': {
        template: "<ui-view>"
      }
    }
  });
});