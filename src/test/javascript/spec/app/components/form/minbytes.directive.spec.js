'use strict';

describe('Directive Tests', function () {
    beforeEach(mockApiAccountCall);
    var elmnt, scope;
    beforeEach(inject(function ($compile, $rootScope) {
        var html = '<div min-bytes>{{1+1}}</div>';
        scope = $rootScope.$new();
        elmnt = angular.element(html);
        $compile(elmnt)(scope);
    }));

    it('shold check content of element ', function () {
        scope.$digest();
        expect(elmnt.html()).toContain('2');
    });

    it('should check that element is present', function () {
        scope.$digest();
        expect(elmnt).not.toBe(null);
    });
});
