describe("Unit: Testing Services", function () {
        describe("Base64 Service:", function () {
                var Base64;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        Base64 = $injector.get('Base64');
                }));

                it('should contain a Base64', function () {
                        expect(Base64).not.toBe(null);
                });

                it('should contain encode function', function () {
                        spyOn(Base64, 'encode').and.callThrough();
                        expect(typeof Base64.encode).toBe('function');
                });

                it('should contain encode function been called', function () {
                        spyOn(Base64, 'encode').and.callThrough();
                        Base64.encode(1);
                        expect(Base64.encode).toHaveBeenCalled();
                });

                it('should contain decode function', function () {
                        spyOn(Base64, 'decode').and.callThrough();
                        expect(typeof Base64.decode).toBe('function');
                });

                it('should contain decode function been called', function () {
                        spyOn(Base64, 'decode').and.callThrough();
                        Base64.decode('abc');
                        expect(Base64.decode).toHaveBeenCalled();
                });
        });
});




