class ProjectService {
  constructor() {
    this.projects = [];
    this.localStorageKey = 'web3-issuetracker/projects';
  }

  getProjectById(projectId) {
    return this.projects.find((project) => project.id === parseInt(projectId)) || null;
  }

  async loadProjects() {
    const projectIds = JSON.parse(localStorage.getItem(this.localStorageKey)) || [];
    const projectPromises = projectIds.map((projectId) => fetch(`/api/projects/${projectId}`).then((res) => res.json()));

    // wait for all projects to load
    const data = await Promise.all(projectPromises);

    // map json data into objects
    this.projects = data.map((project) => new Project(project.id, project.title, []));
  }

  saveProjects() {
    localStorage.setItem(this.localStorageKey, JSON.stringify(this.projects.map((project) => project.id)));
  }

  clearProjects() {
    localStorage.removeItem(this.localStorageKey);
    this.projects = [];
  }

  async addProject(title) {
    const res = await fetch('/api/projects', {
      method: 'POST',
      body: JSON.stringify({ title }),
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

    const project = new Project(data.id, title, []);
    this.projects.push(project);

    this.saveProjects();

    return project;
  }
}
