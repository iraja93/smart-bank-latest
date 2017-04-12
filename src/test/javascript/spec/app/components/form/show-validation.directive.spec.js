'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);
    var elm, scope;
    beforeEach(inject(function ($compile, $rootScope, $injector) {
        var html = '<div sort-validation>Hello</div>';
        scope = $rootScope.$new();
        elm = angular.element(html);
        $compile(elm)(scope);
    }));


});


