"use strict";

$(function () {
    taskList = new TaskList();
    taskList.tasks.push(new Task("Setup todo list"));
    taskList.tasks.push(new Task("Buy milk"));
    taskList.tasks.push(new Task("Read recipe"));
    taskList.tasks.push(new Task("Invite guests"));
    taskList.tasks[0].done = true;

    $('#taskList').append(taskList.render());
});
