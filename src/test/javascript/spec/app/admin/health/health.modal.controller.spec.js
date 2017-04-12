'use strict';

describe('Metric Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('HealthModalController', function() {
        var $scope,uibModalInstance; // actual implementations
        var createController,currentHealth,baseName,subSystemName; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
            uibModalInstance = {        
            // Create a mock object using spies
                close: jasmine.createSpy('uibModalInstance.cancel'),
                dismiss: jasmine.createSpy('uibModalInstance.dismiss'),
                result: {
                    then: jasmine.createSpy('uibModalInstance.result.then')
                }
            };
            var locals = {
                '$scope': $scope,
                '$uibModalInstance':uibModalInstance,
                'currentHealth':currentHealth,
                'baseName':baseName,
                'subSystemName':subSystemName
            };
            createController = function() {
                $injector.get('$controller')('HealthModalController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should evaluate updating metrics to be true', function() {
			 spyOn($scope.vm,'cancel');
     expect(typeof $scope.vm.cancel).toBe('function');
 });
	
	
		 it('should evaluate updating metrics to be true', function() {
			 spyOn($scope.vm,'cancel');
			 $scope.vm.cancel();
     expect($scope.vm.cancel).toHaveBeenCalled();
 });
	
	
	
	
    });
});
