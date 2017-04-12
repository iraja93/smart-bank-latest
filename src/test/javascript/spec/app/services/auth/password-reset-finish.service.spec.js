describe("Unit: Testing Services", function () {
    describe("PasswordResetFinish Service:", function () {
        var PasswordResetFinish;
        beforeEach(inject(function ($injector) {
            angular.module('smartbankApp');
            PasswordResetFinish = $injector.get('PasswordResetFinish');
        }));

        it('should contain a PasswordResetFinish', function () {
            expect(PasswordResetFinish).not.toBe(null);
        });
    });
});




