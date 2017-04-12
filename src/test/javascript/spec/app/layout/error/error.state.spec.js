describe(' register state test', function () {

  var $state;

  beforeEach(function () {
    module('smartbankApp');
    inject(function (_$state_, $templateCache) {
      $state = _$state_;
      $templateCache.put('app/layouts/error/error.html', '');
      $templateCache.put('app/layouts/error/accessdenied.html', '');
    })
  });

  it('should respond to URL', function () {
    expect($state.href('error')).toEqual('#/error');
  });

  it('should respond to URL', function () {
    expect($state.href('accessdenied')).toEqual('#/accessdenied');
  });
});
