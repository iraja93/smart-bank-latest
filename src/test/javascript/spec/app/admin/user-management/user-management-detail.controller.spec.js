'use strict';

describe('UserManagementDialogController  Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('UserManagementDetailController', function() {
        var $scope,User; // actual implementations
        var createController; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
			
            var locals = {
                '$scope': $scope
				
            };
	
            createController = function() {
                $injector.get('$controller')('UserManagementDetailController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should check user parameter', function() {
			 
     expect($scope.vm.user).toEqual({});
 });
		
		
		
		
		
    });
});
