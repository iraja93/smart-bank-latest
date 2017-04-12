'use strict';

describe('Home Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('UserManagementController', function() {
        var $scope; // actual implementations
        var createController,pagingParams,paginationConstants,ParseLinks; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
			  
			 var locals = {
     '$scope': $scope,
     'pagingParams':pagingParams, 
     'paginationConstants':paginationConstants,
     'ParseLinks':ParseLinks
		
 };
			 createController = function() {
     $injector.get('$controller')('UserManagementController as vm', locals);
 };
            createController();
			
		
		    
        }));
  
	
	
	
	
	
    });
});

			
