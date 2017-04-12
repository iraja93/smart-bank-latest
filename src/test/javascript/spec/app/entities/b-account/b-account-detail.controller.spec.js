'use strict';

describe('Controller Tests', function() {

    describe('BAccount Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockBAccount, MockTransaction, MockBenificiary, MockUser;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockBAccount = jasmine.createSpy('MockBAccount');
            MockTransaction = jasmine.createSpy('MockTransaction');
            MockBenificiary = jasmine.createSpy('MockBenificiary');
            MockUser = jasmine.createSpy('MockUser');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'BAccount': MockBAccount,
                'Transaction': MockTransaction,
                'Benificiary': MockBenificiary,
                'User': MockUser
            };
            createController = function() {
                $injector.get('$controller')("BAccountDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'smartbankApp:bAccountUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
