'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);

    var authority, scope;

    beforeEach(inject(function ($compile, $rootScope) {
        var html = '<div has-any-authority>{{1+5}}</div>';
        scope = $rootScope.$new();
        authority = angular.element(html);
        $compile(authority)(scope);
    }));

    it('shold check content of element to be 6 ', function () {
        scope.$digest();
        expect(authority.html()).toContain('6');
    });

    it('should check that has-any-authority  is present', function () {
        scope.$digest();
        expect(authority).not.toBe(null);
    });





});
