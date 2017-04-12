'use strict';

describe('Home Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('HomeController', function() {
        var $scope; // actual implementations
        var createController,LoginService; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
			LoginService=$injector.get('LoginService');
			 var locals = {
     '$scope': $scope,
				
 };
			 createController = function() {
     $injector.get('$controller')('HomeController as vm', locals);
 };
            createController();
        }));
  
	
        it('should check register method in controller', function () {
            spyOn($scope.vm,'register');
            expect(typeof $scope.vm.register).toBe('function');
        });
	
	
	
        it('should check for null account', function() {
            expect($scope.vm.account).toBe(null);
        });
	
	   it('should check for null isAuthenticated', function() {
            expect($scope.vm.isAuthenticated).toBe(null);
        });
	
	
	   it('should check for LoginService methods', function() {
		   spyOn(LoginService,'open').and.callThrough();
		   LoginService.open();
            expect(LoginService.open).toHaveBeenCalled();
        });
	
	
	
	
	
    });
});

			
