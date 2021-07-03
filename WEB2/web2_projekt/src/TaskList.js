"use strict";
let api = 'https://zhaw.herokuapp.com/task_lists/';

function TaskList(title) {
    this.id = null;
    this.tasks = [];
    this.title = title || "";
}

TaskList.prototype.createTask = function (title, done) {
    let _task = new Task(title, done);
    this.tasks.push(_task);
    return _task;
};

TaskList.prototype.render = function () {
    let $tasks = [];
    this.tasks.sort((x, y) => x.done - y.done);
    $.each(this.tasks, function (index, task) {
        $tasks.push(task.render());
    });

    return $('<ul>').append($tasks);
}

TaskList.prototype.toJSON = function () {
    return JSON.stringify({
        id: this.id,
        title: this.title,
        tasks: this.tasks
    });
};

/*
 * Persists the TaskList to the server.
 */
TaskList.prototype.save = function (callback) {
    this.tasks = this.tasks.filter( t => t.title!="" );
    $.post(api + (this.id ? this.id : ""), this.toJSON(), function (data) {
        let object = JSON.parse(data);
        if (!this.id) {
            window.location.hash = object.id;
        }
        if (callback) {
            callback();
        }
    });
};

/*
 * Loads the given TaskList from the server.
 */
TaskList.load = function (id, callback) {
    var taskList = new TaskList();
    $.getJSON(api + id, function (data) {
        taskList.id = data.id;
        if (!data.tasks){
            taskList.createTask("");
        } else {
            $.each(data.tasks, function (index, task) {
                taskList.createTask(task.title, task.done);
            });
        }
        taskList.title = data.title;
        callback(taskList);
    });
}
