describe('capitalize filter', function () {

  var $filter;
  beforeEach(inject(function (_$filter_) {
    $filter = _$filter_;

  }));

  it('should contain filter capitalize', function () {
    var length = $filter('capitalize');
    expect(length).not.toEqual(null);
  });

  it('Should check functionality of filter', function () {
    var res = $filter('capitalize')('abc');
    expect(res).not.toEqual('ABC');
  });
});
