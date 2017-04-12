describe('words filter', function () {
  var $filter;
  beforeEach(inject(function (_$filter_) {
    $filter = _$filter_;
  }));

  it('should contain words filter', function () {
    var length = $filter('words');
    expect(length).not.toEqual(null);
  });

  it('should check functionality of filter', function () {
    var res = $filter('words')('abc', 2);
    expect(res).not.toEqual('a');
  });
});
