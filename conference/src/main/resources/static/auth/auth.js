angular.module('conference').controller('authController', function ($scope, $http, $localStorage, $location) {
    const rootPath = 'http://localhost:8189/conference';

    $scope.tryToAuth = function () {
        $http.post(rootPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $localStorage.authUser = response.data;
                    $scope.authUser = response.data.username;
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $location.path('/');
                }
            }, function errorCallback() {
                window.alert("Ошибка авторизации. Неверный логин/пароль");
            });
    };

    $scope.logout = function () {
        $http.defaults.headers.common.Authorization = null;
        delete $localStorage.authUser
    }
});