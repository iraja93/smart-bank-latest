describe("Unit: Testing Services", function () {
        describe("DataUtils Service:", function () {
                var DataUtils;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        DataUtils = $injector.get('DataUtils');
                }));

                it('should contain a DataUtils', function () {
                        expect(DataUtils).not.toBe(null);
                });

                it('should contain toBase64 function', function () {

                        expect(typeof DataUtils.toBase64).toBe('function');
                });

                it('should contain openFile function', function () {

                        expect(typeof DataUtils.openFile).toBe('function');
                });

                it('should contain abbreviate function', function () {
                        spyOn(DataUtils, 'abbreviate').and.callThrough();
                        expect(typeof DataUtils.abbreviate).toBe('function');
                });

                it('should contain byteSize function', function () {
                        spyOn(DataUtils, 'byteSize').and.callThrough();
                        expect(typeof DataUtils.byteSize).toBe('function');
                });

                it('should contain byteSize function been called', function () {
                        spyOn(DataUtils, 'byteSize').and.callThrough();
                        DataUtils.byteSize();
                        expect(DataUtils.byteSize).toHaveBeenCalled();
                });

                it('should contain abbreviate function been called', function () {
                        spyOn(DataUtils, 'abbreviate').and.callThrough();
                        DataUtils.abbreviate();
                        expect(DataUtils.abbreviate).toHaveBeenCalled();
                });

                it('should contain openFile function been called', function () {
                        spyOn(DataUtils, 'openFile').and.callThrough();
                        DataUtils.openFile();
                        expect(DataUtils.openFile).toHaveBeenCalled();
                });

                it('should contain toBase64 function been called', function () {
                        spyOn(DataUtils, 'toBase64').and.callThrough();
                        DataUtils.toBase64();
                        expect(DataUtils.toBase64).toHaveBeenCalled();
                });
        });
});




