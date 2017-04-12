'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);
    var attr, scope;
    beforeEach(inject(function ($compile, $rootScope) {
        var html = '<div max-bytes>{{"hi"}}</div>';
        scope = $rootScope.$new();
        attr = angular.element(html);
        $compile(attr)(scope);
    }));

    it('shold check content of element ', function () {
        scope.$digest();
        expect(attr.html()).toContain('hi');
    });

    it('should check that element is present', function () {
        scope.$digest();
        expect(attr).not.toBe(null);
    });
});
