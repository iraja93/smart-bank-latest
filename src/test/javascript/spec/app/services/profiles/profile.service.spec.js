describe("Unit: Testing Services", function () {
        describe("ProfileService Service:", function () {
                var ProfileService;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        ProfileService = $injector.get('ProfileService');
                }));

                it('should contain a ProfileService', function () {
                        expect(ProfileService).not.toBe(null);
                });

                it('should contain getProfileInfo function been called', function () {
                        spyOn(ProfileService, 'getProfileInfo').and.callThrough();
                        ProfileService.getProfileInfo();
                        expect(ProfileService.getProfileInfo).toHaveBeenCalled();
                });
        });
});




