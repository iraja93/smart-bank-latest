describe(' register state test', function () {

  var $state;

  beforeEach(function () {
    module('smartbankApp');

    inject(function (_$state_, $templateCache) {
      $state = _$state_;
      $templateCache.put('app/account/reset/request/reset.request.html', '');
    })
  });

  it('should respond to URL', function () {
    expect($state.href('requestReset')).toEqual('#/reset/request');
  });
});
