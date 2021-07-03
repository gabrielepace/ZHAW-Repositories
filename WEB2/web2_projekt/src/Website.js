"use strict";

let currentTaskList;

$(function () {
    let hash = window.location.hash.replace("#", "");

    if (hash) {
        TaskList.load(hash, initTaskList);
    } else {
        let taskList = new TaskList();
        initTaskList(taskList)
    }

    $('#saveTasks').click(function (event) {
        currentTaskList.save(function () {
            $('#notification').slideDown('slow');
            window.setTimeout(function () {
                $('#notification').slideUp('slow')
            }, 3000);
        });
    });

    $('#newTodoList').click(function () {
        window.location.href = "index.html";
    });
});

function initTaskList(taskList) {
    currentTaskList = taskList;
    currentTaskList.createTask('');
    $('#taskList').html(currentTaskList.render());

    let selectEmptyTask = () => {
        let emptyTask;
        $('input.form-control').each(
            function( index, elem ){
                if (elem.value==""){
                    emptyTask = elem;
                }
            });
        return emptyTask
    };

    $('#createTask').click(function (event) {
        event.preventDefault();
        let emptyTask = selectEmptyTask();
        if (emptyTask){
            emptyTask.focus();
        } else {
            let task = currentTaskList.createTask('');
            $('#taskList ul').append(task.render());
            selectEmptyTask().focus();
        }
    });
}
