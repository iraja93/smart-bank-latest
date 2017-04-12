'use strict';

describe('Directive Tests', function () {
	beforeEach(mockApiAccountCall);

	var tag, scope;

	beforeEach(inject(function ($compile, $rootScope) {
		var html = '<div class="ribbon hidden"><a href="" >{{1+1}}</a></div>';
		scope = $rootScope.$new();
		tag = angular.element(html);
		$compile(tag)(scope);

	}));

	it('shold check content of element is 2 ', function () {
		scope.$digest();
		expect(tag.html()).toContain('2');
	});

	it('should check that element "a" is present', function () {
		scope.$digest();
		expect(tag.find('a').length).toEqual(1);

	});

	it('should check that ribbon hidden class is present', function () {
		scope.$digest();
		expect(tag.hasClass('ribbon hidden')).toBe(true);

	});
});
