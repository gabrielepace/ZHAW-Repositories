const Project = require('../models/project');
const Issue = require('../models/issue');

class ProjectController {
  constructor(conn) {
    this.conn = conn;

    this.get = this.get.bind(this);
    this.create = this.create.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
  }

  async get(req, res) {
    const project = await this.getProject(req.params.project, res);

    if (project) {
      res.json(project);
    }

    res.sendFile(path.resolve(__dirname, 'src', 'index.html'));
  }

  async create(req, res) {
    if (!req.body.title || req.body.title.trim().length === 0) {
      return res.status(400).json({ errors: { title: 'Titel ist erforderlich' }});
    }

    const project = new Project(req.body.title);

    await this.conn.manager.save(project);

    res.json(project);
  }

  async update(req, res) {
    if (!req.body.title || req.body.title.trim().length === 0) {
      return res.status(400).json({ errors: { title: 'Titel ist erforderlich' }});
    }

    const project = await this.getProject(req.params.project, res);

    if (project) {
      project.title = req.body.title;
      await this.conn.manager.save(project);

      res.json(project);
    }
  }

  async delete(req, res) {
    const project = await this.getProject(req.params.project, res);

    if (project) {
      const projectRepository = this.conn.getRepository(Project);
      await projectRepository.remove(project);
      res.status(204).send();
    }
  }

  async getProject(projectId, res) {
    const projectRepository = this.conn.getRepository(Project);
    const project = await projectRepository.findOne(projectId);

    if (!project) {
      res.status(404).json({ errors: { project: 'Projekt existiert nicht' }});
      return null;
    }

    return project;
  }
}

module.exports = ProjectController;