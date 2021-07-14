angular.module('conference').controller('speaksController', function ($scope, $http, $localStorage) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    $scope.getMySpeaks = function () {
        $http.get(apiPath + '/my_speaks')
            .then(function (response) {
                console.log(response.data)
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
                console.log($scope.roomList);
            })
    }

    let exampleModal = document.getElementById('changeTalk');
    exampleModal.addEventListener('show.bs.modal', function (event) {
        $scope.getRoomList();
        let button = event.relatedTarget;
        var recipient = JSON.parse(button.getAttribute('data-bs-whatever'));
        console.log(recipient);
        let modalTitle = exampleModal.querySelector('.modal-title');
        let selector = exampleModal.querySelector('.modal-body select')

        modalTitle.textContent = 'Изменение доклада ' + recipient.talkDto.text;
        // selector.options.selectedIndex = $scope.roomList.index(recipient.room);
    })
});