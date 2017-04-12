describe("Unit: Testing Services", function () {
        describe("ParseLinks Service:", function () {
                var ParseLinks;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        ParseLinks = $injector.get('ParseLinks');
                }));

                it('should contain a ParseLinks', function () {
                        expect(ParseLinks).not.toBe(null);
                });

                it('should contain a parse function', function () {
                        spyOn(ParseLinks, 'parse');
                        expect(typeof ParseLinks.parse).toBe('function');
                });

                it('should contain a parse n it has been called', function () {
                        spyOn(ParseLinks, 'parse').and.callThrough();
                        ParseLinks.parse('abc;aaa');
                        expect(ParseLinks.parse).toHaveBeenCalled();
                });
        });
});


