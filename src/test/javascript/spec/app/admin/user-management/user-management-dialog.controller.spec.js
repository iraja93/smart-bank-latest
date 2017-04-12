'use strict';

describe('UserManagementDialogController  Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('UserManagementDialogController', function() {
 var $scope,uibModalInstance; // actual implementations
        var createController,entity; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
		
            var locals = {
                '$scope': $scope,
					'$uibModalInstance': uibModalInstance,
					'entity':entity,
					
            };
			uibModalInstance = {        
            // Create a mock object using spies
        close: jasmine.createSpy('uibModalInstance.cancel'),
        dismiss: jasmine.createSpy('uibModalInstance.dismiss'),
        result: {
          then: jasmine.createSpy('uibModalInstance.result.then')
        }
      };
            createController = function() {
                $injector.get('$controller')('UserManagementDialogController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should check languages parameter', function() {
            expect(($scope.vm.languages)).toEqual(null);
        });
		
		it('should check clear method', function() {
			spyOn($scope.vm,'clear').and.callThrough();
			$scope.vm.clear();
            expect($scope.vm.clear).toHaveBeenCalled();
        });
		
		
		 it('should check authorities parameters', function() {
            expect(($scope.vm.authorities)).toEqual(['ROLE_USER', 'ROLE_ADMIN']);
        });
		
		 it('should check save method', function() {
			 spyOn($scope.vm,'save');
		
            expect(typeof $scope.vm.save).toBe('function');
        });
		
		 it('should check save method', function() {
			
            expect($scope.vm.languages).toBe(null);
        });
		
		
		
		
		
    });
});
