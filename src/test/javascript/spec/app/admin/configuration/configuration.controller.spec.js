'use strict';

describe('Configuration Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('CgConfigurationController', function() {
        var $scope,$filter;// actual implementations
        var createController; // local utility functions
        var $httpBackend;
        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
            $httpBackend = $injector.get('$httpBackend');
            var locals = {
                '$scope': $scope
            };
            createController = function() {
                $injector.get('$controller')('CgConfigurationController as vm', locals);
            };
            createController();
        }));
        
        it('should check for initial configuration to be null', function() {
            expect(($scope.vm.configuration)).toBe(null);
        });
		
		
        it('should check for initial allconfiguration to be null', function() {
            expect(($scope.vm.allConfiguration)).toBe(null);
        });
		
		
		 it('should fetch authentication token', function() {
     $httpBackend.expectGET('management/env');
 });
			
        it('should fetch authentication token 2', function() {
            $httpBackend.expectGET('management/configprops');
        });
    });
});
