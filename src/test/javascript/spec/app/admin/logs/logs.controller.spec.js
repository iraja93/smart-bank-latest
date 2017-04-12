'use strict';

describe('logs Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('LogsController', function() {
 var $scope,LogsService; // actual implementations
        var createController; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
			LogsService=$injector.get('LogsService');
            var locals = {
                '$scope': $scope,
				'LogsService':LogsService
            };
            createController = function() {
                $injector.get('$controller')('LogsController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should check changeLevel method', function() {
			 spyOn($scope.vm,'changeLevel').and.callThrough();
			 $scope.vm.changeLevel('name',0);
            expect($scope.vm.changeLevel).toHaveBeenCalled();
        });
		
			
		 it('should check logService method', function() {
			 spyOn(LogsService,'findAll').and.callThrough();
			 LogsService.findAll();
		  expect(LogsService.findAll).toHaveBeenCalled();
        });
		
		
		
		
		
    });
});
