describe("Unit: Testing Services", function () {
        describe("Principal Service:", function () {
                var Principal;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        Principal = $injector.get('Principal');
                }));


                it('should contain a Principal', function () {
                        expect(Principal).not.toBe(null);
                });

                it('should contain authenticate function been called', function () {
                        spyOn(Principal, 'authenticate').and.callThrough();
                        Principal.authenticate('user');
                        expect(Principal.authenticate).toHaveBeenCalled();
                });

                it('should contain hasAnyAuthority function been called', function () {
                        spyOn(Principal, 'hasAnyAuthority').and.callThrough();
                        Principal.hasAnyAuthority('user');
                        expect(Principal.hasAnyAuthority).toHaveBeenCalled();
                });

                it('should contain hasAuthority function been called', function () {
                        spyOn(Principal, 'hasAuthority').and.callThrough();
                        Principal.hasAuthority('user');
                        expect(Principal.hasAuthority).toHaveBeenCalled();
                });

                it('should contain identity function been called', function () {
                        spyOn(Principal, 'identity').and.callThrough();
                        Principal.identity('user');
                        expect(Principal.identity).toHaveBeenCalled();
                });

                it('should contain isAuthenticated function been called', function () {
                        spyOn(Principal, 'isAuthenticated').and.callThrough();
                        Principal.isAuthenticated();
                        expect(Principal.isAuthenticated).toHaveBeenCalled();
                });

                it('should contain isIdentityResolved function been called', function () {
                        spyOn(Principal, 'isIdentityResolved').and.callThrough();
                        Principal.isIdentityResolved();
                        expect(Principal.isIdentityResolved).toHaveBeenCalled();
                });
        });
});




