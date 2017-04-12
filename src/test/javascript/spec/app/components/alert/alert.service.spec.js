describe("Unit: Testing Services", function () {
        describe("Search Service:", function () {
                var AlertService;
                beforeEach(inject(function ($injector) {
                        angular.module('smartbankApp');
                        AlertService = $injector.get('AlertService');
                }));

                it('should contain a AlertService', function () {
                        expect(AlertService).not.toBe(null);
                });

                it('should check type of closealert', function () {
                        expect(typeof AlertService.closeAlert).toBe('function');
                });

                it('should check type of closeAlertByIndex options', function () {
                        expect(typeof AlertService.closeAlertByIndex).toBe('function');
                });

                it('should check type of isToast', function () {
                        expect(typeof AlertService.isToast).toBe('function');
                });

                it('should check isToast is called ', function () {
                        spyOn(AlertService, 'isToast');
                        AlertService.isToast();
                        expect(AlertService.isToast).toHaveBeenCalled();
                });

                it('should check type of  clear function', function () {
                        expect(typeof AlertService.clear).toBe('function');
                });

                it('should check type of  get function', function () {
                        expect(typeof AlertService.get).toBe('function');
                });

                it('should check type of success', function () {
                        expect(typeof AlertService.success).toBe('function');
                });

                it('should check success is called', function () {
                        spyOn(AlertService, 'success');
                        AlertService.success();
                        expect(AlertService.success).toHaveBeenCalled();
                });

                it('should check type of factory', function () {
                        expect(typeof AlertService.factory).toBe('function');
                });
        });
});


