'use strict';

describe('Controller Tests', function() {

    describe('Benificiary Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBenificiary, MockBAccount;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBenificiary = jasmine.createSpy('MockBenificiary');
            MockBAccount = jasmine.createSpy('MockBAccount');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Benificiary': MockBenificiary,
                'BAccount': MockBAccount
            };
            createController = function() {
                $injector.get('$controller')("BenificiaryDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'smartbankApp:benificiaryUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
