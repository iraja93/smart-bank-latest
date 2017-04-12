/**
 * Created by adhabale on 3/1/2017.
 */
(function() {
    'use strict';

    angular
        .module('smartbankApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('login', {
            url: '/login',
            data: {
                authorities: [],
                pageTitle: 'Login'
            },
            views: {
                'content@': {
                    templateUrl: 'app/components/login/login.html',
                    controller: 'LoginController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
