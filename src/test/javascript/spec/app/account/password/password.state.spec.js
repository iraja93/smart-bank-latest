describe('password state', function () {

  var $state;

  beforeEach(function () {
    module('smartbankApp');
    inject(function (_$state_, $templateCache) {
      $state = _$state_;
      $templateCache.put('app/account/password/password.html', '');
    })
  });

  it('should respond to URL', function () {
    expect($state.href('password')).toEqual('#/password');
  });
});
