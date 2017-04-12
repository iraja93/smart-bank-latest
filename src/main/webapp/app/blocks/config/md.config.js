(function () {
    'use strict';

    angular
        .module('smartbankApp')
        .config(materialDesignConfig);

    function materialDesignConfig() {
        // Initialize material design
        $.material.init();
    }
})();
