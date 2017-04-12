describe('Constants', function () {
  var paginationConstants;

  beforeEach(module('smartbankApp'));
  beforeEach(inject(function ($injector) {
    paginationConstants = $injector.get('paginationConstants');
  }));

  it('should be present', function () {
    expect(paginationConstants).not.toBe(null);
  });

  it('should check itemsPerPage', function () {
    expect(paginationConstants.itemsPerPage).toBe(20);
  });
});
