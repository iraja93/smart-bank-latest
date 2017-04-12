describe("Unit: Testing Services", function() {
    describe("CgHealthService Service:", function() {
        var CgHealthService;
        beforeEach(inject(function($injector) {
            angular.module('smartbankApp');
            CgHealthService = $injector.get('CgHealthService');

        }));


        it('should contain a CgHealthService',function() {
            expect(CgHealthService).not.toBe(null);
        });

			  it('should contain checkHealth function been called',function() {
      spyOn(CgHealthService,'checkHealth').and.callThrough();
      CgHealthService.checkHealth();
      expect(CgHealthService.checkHealth).toHaveBeenCalled();
  });

				  it('should contain transformHealthData function been called',function() {
      spyOn(CgHealthService,'transformHealthData').and.callThrough();
      CgHealthService.transformHealthData();
      expect(CgHealthService.transformHealthData).toHaveBeenCalled();
  });

				  it('should contain getBaseName function been called',function() {
      spyOn(CgHealthService,'getBaseName').and.callThrough();
      CgHealthService.getBaseName();
      expect(CgHealthService.getBaseName).toHaveBeenCalled();
  });
			  it('should contain getSubSystemName function been called',function() {
      spyOn(CgHealthService,'getSubSystemName').and.callThrough();
      CgHealthService.getSubSystemName();
      expect(CgHealthService.getSubSystemName).toHaveBeenCalled();
  });




    });
});


