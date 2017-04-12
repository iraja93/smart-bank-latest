'use strict';

describe('nav bar  Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('NavbarController', function() {
        var $scope; // actual implementations
        var createController; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
			 var locals = {
     '$scope': $scope,
				
 };
			 createController = function() {
     $injector.get('$controller')('NavbarController as vm', locals);
 };
            createController();
        }));
  
	
        it('should check login method in controller', function () {
            spyOn($scope.vm,'login');
            expect(typeof $scope.vm.login).toBe('function');
        });
	
        it('should check logout method in controller', function () {
            spyOn($scope.vm,'logout');
            expect(typeof $scope.vm.logout).toBe('function');
        });

        it('should check toggleNavbar method in controller', function () {
            spyOn($scope.vm,'toggleNavbar');
            expect(typeof $scope.vm.toggleNavbar).toBe('function');
        });
	
        it('should check collapseNavbar method in controller', function () {
            spyOn($scope.vm,'collapseNavbar');
            expect(typeof $scope.vm.collapseNavbar).toBe('function');
        });
	
	
	
    });
});

			
