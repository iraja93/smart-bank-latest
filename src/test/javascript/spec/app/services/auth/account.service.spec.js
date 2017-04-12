describe("Unit: Testing Services", function () {
    describe("Account Service:", function () {
        var Account;
        beforeEach(inject(function ($injector) {
            angular.module('smartbankApp');
            Account = $injector.get('Account');
        }));
        it('should contain a Account', function () {
            expect(Account).not.toBe(null);
        });
    });
});


