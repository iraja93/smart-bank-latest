describe("Unit: Testing Services", function () {
        describe("AuthServerProvider Service:", function () {
                var AuthServerProvider;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        AuthServerProvider = $injector.get('AuthServerProvider');
                }));

                it('should contain a AuthServerProvider', function () {
                        expect(AuthServerProvider).not.toBe(null);
                });

                it('should contain getToken function been called', function () {
                        spyOn(AuthServerProvider, 'getToken').and.callThrough();
                        AuthServerProvider.getToken();
                        expect(AuthServerProvider.getToken).toHaveBeenCalled();
                });

                it('should contain hasValidToken function been called', function () {
                        spyOn(AuthServerProvider, 'hasValidToken').and.callThrough();
                        AuthServerProvider.hasValidToken();
                        expect(AuthServerProvider.hasValidToken).toHaveBeenCalled();
                });

                it('should contain login function been called', function () {
                        spyOn(AuthServerProvider, 'login').and.callThrough();
                        AuthServerProvider.login('amu', 'amu!234', true);
                        expect(AuthServerProvider.login).toHaveBeenCalled();
                });

                it('should contain logout function been called', function () {
                        spyOn(AuthServerProvider, 'logout').and.callThrough();
                        AuthServerProvider.logout();
                        expect(AuthServerProvider.logout).toHaveBeenCalled();
                });
        });
});



