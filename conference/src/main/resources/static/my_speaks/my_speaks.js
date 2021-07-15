angular.module('conference').controller('speaksController', function ($scope, $http, $localStorage, $route) {
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
        $scope.roomList == null ? $scope.getRoomList() : null;
        $scope.speakerList == null ? $scope.getSpeakers() : null;
    }

    $scope.createTalk = function () {
        $http.put(apiPath + '/my_speaks', $scope.createDto())
            .then(function () {
                window.alert("Доклад успешно создан")
                $route.reload();
                $scope.roomList = null;
                $scope.speakerList = null;
            })
    }

    $scope.createDto = function () {
        let selector = document.getElementById('room-select');
        let speakers = document.getElementsByClassName('new-speakers-check')
        let room = selector.options[selector.selectedIndex].label;
        let speakersId = [];

        for (let i = 0; i < speakers.length; i++) {
            if (speakers[i].checked) {
                speakersId.push(speakers[i].value);
            }
        }

        if (speakersId.length === 0) {
            window.alert("Выберите спикеров")
            return null;
        }

        return {
            room: room,
            startTime: $(".new-start-time").val(),
            endTime: $(".new-end-time").val(),
            text: $(".new-text").val(),
            speakers: speakersId
        };
    }
});