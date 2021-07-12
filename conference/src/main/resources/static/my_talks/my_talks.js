angular.module('conference').controller('talksController', function ($scope, $http) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    $scope.getMyTalks = function () {
        $http.get(apiPath+'/my_talks')
            .then(function (response) {
                $scope.myTalksList = response.data;
                console.log($scope.myTalksList)
            })
    }

    $scope.unsubscribe = function (talkId) {
        $http({
            method: 'POST',
            url: apiPath + '/unsub',
            params: {
                talkId: talkId
            },
        })
            .then(function () {
                window.alert("Вы отказались от посещения доклада");
                $scope.getMyTalks();
            })
    }
});