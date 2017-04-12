describe('characters filter', function () {

  var $filter;
  beforeEach(inject(function (_$filter_) {
    $filter = _$filter_;
  }));
  it('should contain filter characters', function () {
    var length = $filter('characters');
    expect(length).not.toEqual(null);
  });

  it('Should check functionality of filter', function () {
    var res = $filter('characters')('abc', 2);
    expect(res).not.toEqual('a');
  });
});
