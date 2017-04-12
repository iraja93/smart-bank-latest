'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);
    var elm, scope;
    beforeEach(inject(function ($compile, $rootScope) {
        var html = '<div class="alerts" ng-cloak="">' +
            '<div ng-repeat="alert in $ctrl.alerts" ng-class="[alert.position, {\'toast\': alert.toast}]">' +
            '<uib-alert ng-cloak="" type="{{alert.type}}" close="alert.close($ctrl.alerts)"><pre ng-bind-html="alert.msg"></pre></uib-alert>' +
            '</div>' +
            '</div>';
        scope = $rootScope.$new();
        elm = angular.element(html);
        $compile(elm)(scope);
    }));

    it('should check that alerts class is present in the template', function () {
        scope.$digest();
        expect(elm.hasClass('alerts')).toBe(true);

    });
});
