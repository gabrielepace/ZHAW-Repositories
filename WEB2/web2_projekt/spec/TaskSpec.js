"use strict";


describe("Task", function () {
    var task;

    beforeEach(function () {
        task = new Task();
    });

    it("Should return a new Task", function () {
        var t = new Task("new task");
        expect(t.title).toBeDefined();
        expect(t.title).toEqual("new task");
        expect(t.done).toEqual(false);
    });

    it("Set a Task to done", function () {
        expect(task.done).toBeFalsy();
        task.done = true;
        expect(task.done).toEqual(true);
    });

    it("Returns same Task title", function () {
        expect(task.title).toEqual("");
        task.title = 'Test Task';
        expect(task.title).toEqual('Test Task');
    });

    it("Set a new Task title via constructor", function () {
        var task2 = new Task("Title set test");
        expect(task2.title).toEqual("Title set test");
    });

    describe("Render", function() {
        it("Renders an unchecked checkbox", function() {
            var $markup = task.render();
            expect($markup.find('input[name=done]')).not.toBeChecked();
        });
        it("Renders an empty input", function() {
            var $markup = task.render();
            expect($markup.find('input[name=title]')).toHaveValue('')
        });
        it("Checks the checkbox when done", function() {
            task.done = true;
            var $markup = task.render();
            expect($markup.find('input[name=done]')).toBeChecked();
        });
        it("Renders a title", function() {
            task.title = 'task title';
            var $markup = task.render();
            expect($markup.find('input[name=title]')).toHaveValue('task title');
        });
    });
});
