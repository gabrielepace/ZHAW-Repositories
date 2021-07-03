"use strict";

function Task(title, done=false) {
    this.done = done;
    this.title = title || "";
}

Task.prototype.render = function () {
    let $markup;
    let $done = $('<input>', {
        name: 'done',
        type: 'checkbox',
        class: 'form-check-input',
        checked: this.done
    });

    let $title = $('<input>', {
        name: 'title',
        type: 'text',
        class: 'form-control'
    }).val(this.title);

    $markup = $('<li>', {class: 'form-check'}).append([$done, $title]);
    if (this.done) {
        $markup.addClass("finished");
    }

    $markup.data('task', this);

    $markup.find('input').change(function (event) {
        let input = $(this);
        let parent_element = input.parent();
        let task = parent_element.data('task');
        let name = input.attr("name");

        if (name === 'done') {
            let checked = input.is(":checked");
            let unorderedList = parent_element.parent();
            if (checked) {
                parent_element.slideUp(300, () => {
                    unorderedList.append(parent_element);
                    parent_element.addClass('finished');
                }).slideDown('slow');
                task.done = true;
            } else {
                parent_element.slideUp(300, () => {
                    unorderedList.prepend(parent_element);
                    parent_element.removeClass('finished');
                }).slideDown('slow');
                task.done = false;
            }
        } else if (name == "title") {
            task.title = input.val();
        }

    });
    return $markup;
};
