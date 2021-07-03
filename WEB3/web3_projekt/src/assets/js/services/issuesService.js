class IssuesService {
  async loadIssuesByProject(projectId) {
    const res = await fetch(`/api/projects/${projectId}/issues`);
    const data = await res.json();

    const issues = data.map((issue) => new Issue(issue.id, issue.priority, issue.title, issue.dueDate, issue.done));

    return issues;
  }

  async saveIssue(projectId, issue) {
    const res = await fetch(`/api/projects/${projectId}/issues${issue.id ? `/${issue.id}` : ''}`, {
      method: issue.id ? 'PUT' : 'POST',
      body: JSON.stringify({ title: issue.title, priority: issue.priority, dueDate: issue.dueDate ? issue.dueDate.toISOString().split('T')[0] : null, done: issue.done }),
      headers: {
        'Content-Type': 'application/json',
      },
    });

    const data = await res.json();

    // check for server-side errors
    if (data.errors) {
      const error = new Error('Validierung fehlgeschlagen');
      error.errors = data.errors;
      throw error;
    }

    issue.id = data.id;
    return issue;
  }

  async deleteIssue(projectId, issueId) {
    await fetch(`/api/projects/${projectId}/issues/${issueId}`, {
      method: 'DELETE',
    });
  }
}
