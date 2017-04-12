'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);

    var elm, scope, $httpBackend;

    beforeEach(inject(function($compile, $rootScope, $injector) {
        $httpBackend = $injector.get('$httpBackend');

        var html = '<div active-link></div>';
        scope = $rootScope.$new();
        elm = angular.element(html);
        $compile(elm)(scope);
        $httpBackend.flush();
    }));

  
});
