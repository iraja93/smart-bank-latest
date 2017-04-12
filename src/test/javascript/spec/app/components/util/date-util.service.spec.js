describe("Unit: Testing Services", function () {
        describe("DateUtils Service:", function () {
                var DateUtils;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        DateUtils = $injector.get('DateUtils');
                }));

                it('should contain a DateUtils', function () {
                        expect(DateUtils).not.toBe(null);
                });

                it('should contain a dateformat', function () {
                        expect(typeof DateUtils.dateformat).toBe('function');
                });

                it('should contain a convertLocalDateToServer', function () {
                        expect(typeof DateUtils.convertLocalDateToServer).toBe('function');
                });

                it('should contain a convertLocalDateFromServer', function () {
                        expect(typeof DateUtils.convertLocalDateFromServer).toBe('function');
                });

                it('should contain a convertDateTimeFromServer', function () {
                        expect(typeof DateUtils.convertDateTimeFromServer).toBe('function');
                });

                it('should contain byteSize function been called', function () {
                        spyOn(DateUtils, 'convertDateTimeFromServer').and.callThrough();
                        DateUtils.convertDateTimeFromServer();
                        expect(DateUtils.convertDateTimeFromServer).toHaveBeenCalled();
                });

                it('should contain convertLocalDateFromServer function been called', function () {
                        spyOn(DateUtils, 'convertLocalDateFromServer').and.callThrough();
                        DateUtils.convertLocalDateFromServer();
                        expect(DateUtils.convertLocalDateFromServer).toHaveBeenCalled();
                });

                it('should contain convertLocalDateToServer function been called', function () {
                        spyOn(DateUtils, 'convertLocalDateToServer').and.callThrough();
                        DateUtils.convertLocalDateToServer();
                        expect(DateUtils.convertLocalDateToServer).toHaveBeenCalled();
                });

                it('should contain dateformat function been called', function () {
                        spyOn(DateUtils, 'dateformat').and.callThrough();
                        DateUtils.dateformat();
                        expect(DateUtils.dateformat).toHaveBeenCalled();
                });

                it('should contain a dateformat', function () {
                        spyOn(DateUtils, 'dateformat').and.callThrough();
                        DateUtils.dateformat();
                        expect(DateUtils.dateformat()).toEqual('yyyy-MM-dd');
                });
        });
});


