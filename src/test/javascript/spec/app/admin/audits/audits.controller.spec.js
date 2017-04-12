'use strict';

describe('Audit Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('AuditsController', function() {
        var $scope,$filter,AuditsService,DummyAuditsService; // actual implementations
        var createController,ParseLinks; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
            var locals = {
                '$scope': $scope
            };
            createController = function() {
                $injector.get('$controller')('AuditsController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should check page parameter', function() {
     expect(($scope.vm.page)).toBe(1);
 });
		
        it('should check for null audits', function() {
            expect(($scope.vm.audits)).toBe(null);
        });
		
        it('should check for from date to be greater than to date', function() {
            expect(($scope.vm.fromDate)<($scope.vm.toDate)).toBeTruthy();
        });
		
        it('should check for initial total items to be null', function() {
            expect($scope.vm.totalItems).toBe(null);
        });
		
        it('should check today function', function() {
            spyOn($scope.vm,'today');
            expect(typeof $scope.vm.today).toBe('function');
        });
		
		
    });
});
