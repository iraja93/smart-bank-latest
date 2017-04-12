/**
 * Created by adhabale on 2/21/2017.
 */
(function() {
    'use strict';

    angular
        .module('smartbankApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('views', {
            abstract: true,
            parent: 'app'
        });
    }
})();
