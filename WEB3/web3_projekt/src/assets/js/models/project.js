class Project {
  constructor(id, title, issues) {
    this.id = id;
    this.title = title;
    this.issues = issues;
  }

  async addIssue(priority, title, dueDate, done) {
    const issue = await new IssuesService().saveIssue(this.id, new Issue(0, priority, title, dueDate, done));

    this.issues.push(issue);

    return issue;
  }

  deleteIssueById(issueId) {
    this.issues = this.issues.filter((issue) => issue.id !== issueId);
  }

  getIssueById(issueId) {
    return this.issues.find((issue) => issue.id === issueId) || null;
  }

  async loadIssues() {
    this.issues = await new IssuesService().loadIssuesByProject(this.id);
  }
}