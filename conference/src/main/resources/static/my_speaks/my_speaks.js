angular.module('conference').controller('speaksController', function ($scope,$http) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    $scope.getMySpeaks = function () {
        $http.get(apiPath+'/my_speaks')
            .then(function (response) {
                $scope.mySpeaksList = response.data;
                console.log($scope.mySpeaksList)
            })
    }
});