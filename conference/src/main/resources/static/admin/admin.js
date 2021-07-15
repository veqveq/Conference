angular.module('conference').controller('adminController', function ($scope, $http, $route, $localStorage) {
        const rootPath = 'http://localhost:8189/conference';
        const apiPath = rootPath + '/api/v1/users'

        $scope.getUsers = function () {
            $http.get(apiPath)
                .then(function (response) {
                    $scope.userList = response.data;
                })
        }

        $scope.getRoles = function () {
            if ($scope.roleList == null) {
                $http.get(apiPath + '/roles')
                    .then(function (response) {
                        $scope.roleList = response.data
                    })
            }
        }

        $scope.changeRole = function (roleId, userId) {
            $http.post(apiPath + '/roles',
                {
                    userId: userId,
                    roleId: roleId
                }
            ).then(
                function successCallback() {
                    $route.reload()
                }, function errorCallback() {
                    window.alert("Ошибка смены роли");
                });
        };

        $scope.checkMe = function (login) {
            return $localStorage.authUser.username === login;
        }

        $scope.delete = function (userId) {
            $http({
                method: 'DELETE',
                url: apiPath,
                params: {
                    userId: userId
                }
            })
                .then(
                    function successCallback() {
                        $route.reload()
                    }, function errorCallback() {
                        window.alert("Не удалось удалить пользователя");
                    });
        }

        $scope.cleanForm = function () {
            $scope.newUser ? $scope.newUser.firstName = null : null;
            $scope.newUser ? $scope.newUser.lastName = null : null;
        }

        $scope.createUser = function () {
            let selector = document.getElementById("new-user-sel");
            let roleId = selector.options[selector.selectedIndex].value;

            $http.put(apiPath, {
                login: $scope.newUser ? $scope.newUser.firstName : null,
                password: $scope.newUser ? $scope.newUser.lastName : null,
                roleId: roleId
            }).then(
                function successCallback() {
                    $scope.cleanForm()
                    $route.reload()
                }, function errorCallback() {
                    window.alert("Ошибка создания пользователя");
                });
        }

    }
);