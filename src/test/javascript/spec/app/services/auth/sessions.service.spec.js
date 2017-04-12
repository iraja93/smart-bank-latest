describe("Unit: Testing Services", function () {
    describe("Sessions Service:", function () {
        var Sessions;
        beforeEach(inject(function ($injector) {
            angular.module('smartbankApp');
            Sessions = $injector.get('Sessions');
        }));

        it('should contain a Sessions', function () {
            expect(Sessions).not.toBe(null);
        });
    });
});




