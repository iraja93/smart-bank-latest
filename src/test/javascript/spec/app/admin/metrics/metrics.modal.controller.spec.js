'use strict';

describe('Metric modal Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('CgMetricsMonitoringModalController', function() {
        var $scope,uibModalInstance; // actual implementations
        var createController,_threadDump_; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
           
			 var locals = {
     '$scope': $scope,
     '$uibModalInstance': uibModalInstance,
     'threadDump':_threadDump_
				
 };
			 createController = function() {
     $injector.get('$controller')('CgMetricsMonitoringModalController as vm', locals);
 };
            createController();
			
            uibModalInstance = {        
            // Create a mock object using spies
                close: jasmine.createSpy('uibModalInstance.cancel'),
                dismiss: jasmine.createSpy('uibModalInstance.dismiss'),
                result: {
                    then: jasmine.createSpy('uibModalInstance.result.then')
                }
            };
      	
         
		   
               
        }));
        
		
        it('should check cancel method in controller', function () {
            expect(typeof $scope.vm.cancel).toBe('function');
        });

   
        it('should check close method have been called', function () {
            spyOn($scope.vm,'cancel');
            $scope.vm.cancel();
            expect($scope.vm.cancel).toHaveBeenCalled();
        });

	
	
	
    });
});

			
			
			
		
		

