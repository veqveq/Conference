angular.module('conference').controller('modalController', function ($scope, $http, $route, $localStorage) {
    const rootPath = 'http://localhost:8189/conference';
    const apiPath = rootPath + '/api/v1/schedule'

    let exampleModal = document.getElementById('changeTalk');
    let speakers = exampleModal.getElementsByClassName('speakers-check');
    let selector = exampleModal.querySelector('.modal-body select');
    let modal = new bootstrap.Modal(document.getElementById('changeTalk'));


    exampleModal.addEventListener('show.bs.modal', function (event) {
        let button = event.relatedTarget;
        let schedule = JSON.parse(button.getAttribute('data-bs-whatever'));
        let modalTitle = exampleModal.querySelector('.modal-title');
        let index = $scope.roomList.findIndex(e1 => e1 === schedule.room);
        let startTime = exampleModal.querySelector('.start-time');
        let endTime = exampleModal.querySelector('.end-time');
        let text = exampleModal.querySelector('.text');
        let talk = schedule.talkDto;
        $scope.talkId = talk.id;
        $scope.sheduleId = schedule.id;

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

    exampleModal.addEventListener('hide.bs.modal', function () {
        for (let i = 0; i < speakers.length; i++) {
            speakers[i].checked = false;
        }
    })

    $scope.checkSpeaker = function (name, talk) {
        for (let i = 0; i < talk.speakers.length; i++) {
            if ((name == talk.speakers[i].id)) {
                return true;
            }
        }
        return false;
    }

    $scope.delete = function () {
        console.log($scope.talkId);
        $http({
            method: 'DELETE',
            url: apiPath,
            params: {
                talkId: $scope.sheduleId != null ? $scope.sheduleId : null
            }
        }).then(function () {
            window.alert("Доклад удалён!")
            modal.hide();
            $route.reload();
        })
    }

    $scope.update = function () {
        $http.post(apiPath + '/my_speaks', $scope.createDto())
            .then(function () {
                window.alert("Доклад успешно обновлён!")
                modal.hide();
                $route.reload();
            })
    }

    $scope.createDto = function () {
        let room = selector.options[selector.selectedIndex].label;
        let speakersId = [];

        for (let i = 0; i < speakers.length; i++) {
            if (speakers[i].checked) {
                speakersId.push(speakers[i].value);
            }
        }
        return {
            id: $scope.sheduleId,
            room: room,
            startTime: $(".start-time").val(),
            endTime: $(".end-time").val(),
            talkId: $scope.talkId,
            text: $(".text").val(),
            speakers: speakersId
        };
    }
});