/**
 * Created by Nicolas on 27/09/2016.
 */
angular.module("pois-app").directive("notInList", function(){
    return {
        restrict: 'A',
        scope: {
            notInList: '='
        },
        require: 'ngModel',
        link: function(scope, ele, attrs, ctrl) {
            ctrl.$parsers.unshift(function (value) {
                var valid = !_.includes(scope.notInList, value)
                if (value) {
                    ctrl.$setValidity('valueInList', valid);
                }
                var buttom = ele.offsetParent().find('button')
                buttom.prop("disabled",!valid);

                return value;
            });

        }}
});
