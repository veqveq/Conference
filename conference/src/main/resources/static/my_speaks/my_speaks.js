angular.module('conference').controller('speaksController', function ($scope, $http, $localStorage) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    $scope.getMySpeaks = function () {
        $http.get(apiPath + '/my_speaks')
            .then(function (response) {
                $scope.mySpeaksList = response.data
            })
    }

    $scope.checkOwner = function (username) {
        return username === $localStorage.authUser.username;
    }

    $scope.getRoomList = function () {
        $http.get(rootPath + '/api/v1/rooms/numb_list')
            .then(function (response) {
                $scope.roomList = response.data;
            })
    }

    $scope.getSpeakers = function () {
        $http.get(rootPath + '/api/v1/users/speakers')
            .then(function (response) {
                $scope.speakerList = response.data;
            })
    }

    $scope.initialModal = function () {
        $scope.getRoomList();
        $scope.getSpeakers();
    }
});