'use strict';

describe('Metric Controller Test', function() {

    beforeEach(mockApiAccountCall);
    beforeEach(mockI18nCalls);

    describe('CgMetricsMonitoringController', function() {
        var $scope,$httpBackend; // actual implementations
        var createController,ParseLinks,CgMetricsService,uibModal; // local utility functions

        beforeEach(inject(function ($injector) {
            $scope = $injector.get('$rootScope').$new();
            $httpBackend=$injector.get('$httpBackend');
		    CgMetricsService=$injector.get('CgMetricsService');
            var locals = {
                '$scope': $scope,
                '$uibModal':uibModal
            };
            createController = function() {
                $injector.get('$controller')('CgMetricsMonitoringController as vm', locals);
            };
            createController();
        }));
        
		
		 it('should evaluate updating metrics to be true', function() {
     expect($scope.vm.updatingMetrics).toBe(true);
 });
	
		 it('should fetch data from valid url', function() {
     $httpBackend.expectGET('management/jhipster/metrics');
 });

		
		 it('should fetch data from valid url', function() {
     $httpBackend.expectGET('management/dump');
 });

		
	 it('should check type of refresh', function(){
		  spyOn($scope.vm, 'refresh');
     expect(typeof $scope.vm.refresh).toBe('function');
 });
	
	 it('should check type of refreshThreadDumpData', function(){
		  spyOn($scope.vm, 'refreshThreadDumpData');
     expect(typeof $scope.vm.refreshThreadDumpData).toBe('function');
 });
		
		 it('should check type of updatingMetrics', function(){
		  spyOn($scope.vm, 'refreshThreadDumpData');
     expect(typeof $scope.vm.refresh.updatingMetrics).not.toBe('function');
 });
	
	 it('should check service has a method ', function(){
		  	  spyOn(CgMetricsService, 'getMetrics');
     expect(typeof CgMetricsService.getMetrics).toBe('function');
 });
	
	
        it('should check service method been called', function(){
		  	  spyOn(CgMetricsService, 'getMetrics');
			  CgMetricsService.getMetrics();
            expect(CgMetricsService.getMetrics).toHaveBeenCalled();
        });
	

	
        it('should check service method been called', function(){
			 spyOn($scope.vm, 'refresh').and.callThrough();
		  	$scope.vm.refresh();
		      expect($scope.vm.refresh).toHaveBeenCalled();
        });
	


   
		
	
	
	
	
    });
});
