'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);

    var elmnt, scope;

    beforeEach(inject(function($compile, $rootScope) {
        var html = '<div class="alerts" ng-cloak="">' +
                        '<div ng-repeat="alert in $ctrl.alerts" ng-class="[alert.position, {\'toast\': alert.toast}]">' +
                            '<uib-alert ng-cloak="" type="{{alert.type}}" close="alert.close($ctrl.alerts)"><pre>{{ alert.msg }}</pre></uib-alert>' +
                        '</div>' +
                  '</div>';
        scope = $rootScope.$new();
        elmnt = angular.element(html);
       $compile(elmnt)(scope);

    }));
	


		
	it('should check that alerts class is present',function(){
		scope.$digest();
	expect(elmnt.hasClass('alerts')).toBe(true);
	
	});

	
	
	
});
