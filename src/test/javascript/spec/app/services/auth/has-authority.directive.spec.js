'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);

    var elm, scope;
    beforeEach(inject(function ($compile, $rootScope, $injector) {
        var html = '<div has-authority>{{1+1}}</div>';
        scope = $rootScope.$new();
        elm = angular.element(html);
        $compile(elm)(scope);
    }));

    it('shold check content of element ', function () {
        scope.$digest();
        expect(elm.html()).toContain('2');
    });

    it('should check that element is present', function () {
        scope.$digest();
        expect(elm).not.toBe(null);
    });
});
