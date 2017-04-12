describe("Unit: Testing Services", function () {
        describe("PaginationUtil Service:", function () {
                var PaginationUtil;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        PaginationUtil = $injector.get('PaginationUtil');
                }));

                it('should contain a PaginationUtil', function () {
                        expect(PaginationUtil).not.toBe(null);
                });

                it('should contain parsePage function been called', function () {
                        spyOn(PaginationUtil, 'parsePage').and.callThrough();
                        PaginationUtil.parsePage(1);
                        expect(PaginationUtil.parsePage).toHaveBeenCalled();
                });

                it('should contain parseAscending function been called', function () {
                        spyOn(PaginationUtil, 'parseAscending').and.callThrough();
                        PaginationUtil.parseAscending('ABC');
                        expect(PaginationUtil.parseAscending).toHaveBeenCalled();
                });

                it('should contain parsePredicate function been called', function () {
                        spyOn(PaginationUtil, 'parsePredicate').and.callThrough();
                        PaginationUtil.parsePredicate('ABC');
                        expect(PaginationUtil.parsePredicate).toHaveBeenCalled();
                });
        });
});



