'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);
    var elm, scope;
    beforeEach(inject(function ($compile, $rootScope) {

        var html = '<div jh-sort>{{"Hello"}}</div>';
        scope = $rootScope.$new();
        elm = angular.element(html);
        $compile(elm)(scope);
    }));

    it('shold check content of element ', function () {
        scope.$digest();
        expect(elm.html()).toContain('Hello');
    });

});


