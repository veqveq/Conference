angular.module('conference').controller('homeController', function ($scope, $http, $route) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    $scope.getSchedule = function () {
        $http.get(apiPath)
            .then(function (response) {
                $scope.scheduleList = response.data;
                console.log($scope.scheduleList);
            })
    }

    $scope.subscribe = function (talkId) {
        $http({
            method: 'POST',
            url: apiPath + '/sub',
            params: {
                talkId: talkId
            },
        })
            .then(function successCallback(response) {
                window.alert("Вы зарегистрировались на лекцию");
                $route.reload();
            }, function errorCallback() {
                window.alert("Вы уже зарегистрированы на эту лекцию");
            });
    }
});

