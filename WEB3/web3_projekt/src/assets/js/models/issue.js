class Issue {
  constructor(id, priority, title, dueDate, done) {
    this.id = id;
    this.priority = priority;
    this.title = title;
    this.dueDate = dueDate ? new Date(dueDate) : null;
    this.done = done;
  }

  setDone(done) {
    this.done = done;
  }
}