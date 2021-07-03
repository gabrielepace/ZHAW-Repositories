"use strict";

describe("TaskList", function() {
    var taskList;

    beforeEach(function() {
        localStorage.clear();
        taskList = new TaskList();
        taskList.id = '42';
    });

    it("Should add a new Element in TaskList", function() {
        taskList.createTask('test');
        expect(taskList.tasks[0].title).toEqual('test');
    });

    it("Adds a new element", function () {
        taskList.createTask('test');
        expect(taskList.tasks[0].title).toEqual('test');
    });

    describe("Render", function() {
        it("Renders empty list when empty", function() {

            expect(taskList.render()).toBeEmpty();
        });
        it("Renders tasks", function() {
            taskList.createTask('test task');
            expect(taskList.render().find('input[name=title]').val()).
            toBe('test task');
        });
    });
});