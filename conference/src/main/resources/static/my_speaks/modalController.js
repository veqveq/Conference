angular.module('conference').controller('modalController', function ($scope,$http) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    let exampleModal = document.getElementById('changeTalk');
    let speakers = exampleModal.getElementsByClassName('speakers-check');

    exampleModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let schedule = JSON.parse(button.getAttribute('data-bs-whatever'));
        let modalTitle = exampleModal.querySelector('.modal-title');
        let selector = exampleModal.querySelector('.modal-body select');
        let index = $scope.roomList.findIndex(e1 => e1 === schedule.room);
        let startTime = exampleModal.querySelector('.start-time');
        let endTime = exampleModal.querySelector('.end-time');
        let text = exampleModal.querySelector('.text');
        let talk = schedule.talkDto;
        $scope.talkId = talk.id;

        modalTitle.textContent = 'Изменение доклада ' + talk.text;
        selector.options.selectedIndex = index;
        startTime.value = schedule.startTime;
        endTime.value = schedule.endTime;
        text.value = talk.text;

        for (let a = 0; a < speakers.length; a++) {
            if ($scope.checkSpeaker(speakers[a].value, talk)) {
                speakers[a].checked = true;
            }
        }
    })
    exampleModal.addEventListener('hide.bs.modal', function (event) {
        for (let i = 0; i < speakers.length; i++) {
            speakers[i].checked = false;
        }
    })
    $scope.checkSpeaker = function (name, talk) {
        for (let i = 0; i < talk.speakers.length; i++) {
            if ((name === talk.speakers[i].login)) {
                return true;
            }
        }
        return false;
    }

    $scope.delete = function () {
        console.log($scope.talkId);
    }

    $scope.update = function () {
        console.log($scope.talkId);
        console.log($scope.updTalk ? $scope.updTalk.startTime : null);
        $http({
            method:'POST',
            url:''
        })
    }
});