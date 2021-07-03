class Issue {
  constructor(priority, title, dueDate, done) {
    this.priority = priority;
    this.title = title;
    this.dueDate = dueDate;
    this.done = done;
  }
}

module.exports = Issue;
