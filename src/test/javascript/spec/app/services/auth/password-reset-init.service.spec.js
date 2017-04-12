describe("Unit: Testing Services", function () {
    describe("PasswordResetInit Service:", function () {
        var PasswordResetInit;
        beforeEach(inject(function ($injector) {
            angular.module('smartbankApp');
            PasswordResetInit = $injector.get('PasswordResetInit');
        }));

        it('should contain a PasswordResetInit', function () {
            expect(PasswordResetInit).not.toBe(null);
        });
    });
});




