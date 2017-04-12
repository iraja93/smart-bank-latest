describe(' register state test', function () {

  var $state;

  beforeEach(function () {
    module('smartbankApp');
    inject(function (_$state_, $templateCache) {
      $state = _$state_;
      $templateCache.put('app/account/audits/audits.html', '');
    })
  });

  it('should respond to URL', function () {
    expect($state.href('audits')).toEqual('#/audits');
  });
});
