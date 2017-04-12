(function() {
    'use strict';

    angular
        .module('smartbankApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
            parent: 'app',
            url: '/home',
            data: {
                authorities: ['ROLE_USER']
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/home.html',
                    controller: 'HomeController',
                    controllerAs: 'vm'
                }
            }
        })
            .state('home.accInfo',{
                url:'/accInfo',
                templateUrl:"app/views/Banking/accInfo/accInfo.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })
          
            .state('home.bills',{
                url:'/bills',
                templateUrl:"app/views/Banking/accInfo/bills.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })

            .state('home.payBills',{
                url:'/payBills',
                templateUrl:"app/views/Banking/accInfo/payBills.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })
            .state('home.transac',{
                url:'/transac',
                templateUrl:"app/views/Banking/accInfo/transac.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })
            .state('home.fundTx',{
                url:'/fundTx',
                templateUrl:"app/views/Banking/accInfo/fundTx.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })
            .state('home.profile',{
                url:'/profile',
                templateUrl:"app/views/Banking/accInfo/profile.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })
            .state('home.beneficiary',{
                url:'/beneficiary',
                templateUrl:"app/views/Banking/accInfo/beneficiary.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })
              .state('home.creditcard',{
                url:'/creditcard',
                templateUrl:"app/views/Banking/accInfo/creditcard.html",
                controller:'DetailsController',
                controllerAs: 'vm'
            })

    }
})();
