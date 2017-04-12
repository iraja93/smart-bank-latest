describe("Unit: Testing Services", function () {
    describe("Activate Service:", function () {
        var Activate;
        beforeEach(inject(function ($injector) {
            angular.module('smartbankApp');
            Activate = $injector.get('Activate');
        }));
        it('should contain a Activate', function () {
            expect(Activate).not.toBe(null);
        });
    });
});


