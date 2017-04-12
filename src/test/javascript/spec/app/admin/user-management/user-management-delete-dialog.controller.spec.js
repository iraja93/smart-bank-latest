'use strict';

describe('UserManagementDialogController  Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('UserManagementDeleteController', function() {
 var $scope,uibModalInstance; // actual implementations
        var createController,entity,User; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
			User=$injector.get('User');
            var locals = {
                '$scope': $scope,
					'$uibModalInstance': uibModalInstance,
					'entity':entity,
					'User':User
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
                $injector.get('$controller')('UserManagementDeleteController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should check languages parameter', function() {
			 spyOn($scope.vm,'clear');
		
            expect(typeof $scope.vm.clear).toBe('function');
        });

		
		 it('should check languages parameter', function() {
			 spyOn($scope.vm,'clear').and.callThrough();
		 $scope.vm.clear();
            expect($scope.vm.clear).toHaveBeenCalled();
        });

		 it('should check languages parameter', function() {
			 spyOn($scope.vm,'confirmDelete');
		
            expect(typeof $scope.vm.confirmDelete).toBe('function');
        });
		
		 it('should check languages parameter', function() {
			 spyOn(User,'delete');
		
            expect(typeof User.delete).toBe('function');
        });
		
		
		
		
    });
});
	