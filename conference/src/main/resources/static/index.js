(function () {
    'use strict';

    angular
        .module('conference', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/admin', {
                templateUrl: 'admin/admin.html',
                controller: 'adminController'
            })
            .when('/speaks', {
                templateUrl: 'my_speaks/my_speaks.html',
                controller: 'speaksController'
            })
            .when('/talks', {
                templateUrl: 'my_talks/my_talks.html',
                controller: 'talksController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.authUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.authUser.token;
        }
    }
})();

angular.module('conference').controller('indexController', function ($scope, $http, $localStorage) {

    $scope.getUser = function () {
        return $localStorage.authUser;
    }

    $scope.userLogged = function () {
        return $localStorage.authUser != null;
    };

    $scope.getUserRole = function () {
        return $localStorage.authUser ? $localStorage.authUser.role : null;
    }
});