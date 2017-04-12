describe("Unit: Testing Services", function () {
    describe("CgConfigurationService Service:", function () {
        var CgConfigurationService;
        beforeEach(inject(function ($injector) {
            angular.module('smartbankApp');
            CgConfigurationService = $injector.get('CgConfigurationService');

        }));
        
        it('should contain a CgConfigurationService', function () {
            expect(CgConfigurationService).not.toBe(null);
        });

        it('should contain getEnv function', function () {
            spyOn(CgConfigurationService, 'getEnv').and.callThrough();
            CgConfigurationService.getEnv();
            expect(CgConfigurationService.getEnv).toHaveBeenCalled();
        });

        it('should contain get function', function () {
            spyOn(CgConfigurationService, 'get').and.callThrough();
            CgConfigurationService.get();
            expect(CgConfigurationService.get).toHaveBeenCalled();
        });
    });
});




