describe('auth expired interceptor', function () {
  var $httpProvider;

  beforeEach(function () {
    // First we initialise a your module, we will get $httpProvider
    // from it and then use that to assert on.
    module('smartbankApp', function (_$httpProvider_) {
      $httpProvider = _$httpProvider_;
    });
    inject();
  });

  it('should add to $httpProvider interceptors', function () {
    expect($httpProvider.interceptors).not.toBe(null);
  });
});
