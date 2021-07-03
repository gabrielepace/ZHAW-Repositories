const Issue = require('../models/issue');
const Project = require('../models/project');
const ProjectController = require('../controllers/project');

class IssueController {
  constructor(conn) {
    this.conn = conn;

    this.get = this.get.bind(this);
    this.create = this.create.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
  }

  async get(req, res) {
    const project = new ProjectController(this.conn).getProject(req.params.project, res);

    if (project) {
      const issuesRepository = this.conn.getRepository(Issue);
      const issues = await issuesRepository.find({ where: { project_id: req.params.project }, order: { id: 'ASC' } });
      res.json(issues);
    }
  }

  async create(req, res) {
    if (!this.validate(req, res)) {
      return;
    }

    const projectRepository = this.conn.getRepository(Project);
    const project = await projectRepository.findOne(req.params.project);

    if (!project) {
      return res.status(404).json({ error: 'Projekt existiert nicht' });
    }

    const issue = new Issue(req.body.priority, req.body.title, req.body.dueDate, req.body.done);
    issue.project_id = req.params.project;

    await this.conn.manager.save(issue);

    res.json(issue);
  }

  async update(req, res) {
    if (!this.validate(req, res)) {
      return;
    }

    const issue = await this.getIssue(req.params.issue, res);

    if (issue) {
      issue.title = req.body.title;
      issue.priority = req.body.priority;
      issue.dueDate = req.body.dueDate;
      issue.done = req.body.done;

      await this.conn.manager.save(issue);

      res.json(issue);
    }
  }

  async delete(req, res) {
    const issue = await this.getIssue(req.params.issue, res);

    if (issue) {
      const issuesRepository = this.conn.getRepository(Issue);
      await issuesRepository.remove(issue);
      res.status(204).send();
    }
  }

  async getIssue(issueId, res) {
    const issuesRepository = this.conn.getRepository(Issue);
    const issue = await issuesRepository.findOne(issueId);

    if (!issue) {
      res.status(404).json({ error: 'Issue existiert nicht' });
      return null;
    }

    return issue;
  }

  validate(req, res) {
    const errors = {};

    // validate priority
    if (!req.body.priority) {
      errors.priority = 'Priorität ist erforderlich';
    } else if (['hoch', 'mittel', 'tief'].indexOf(req.body.priority) < 0) {
      errors.priority = 'Priorität ungültig';
    }

    // validate title
    if (!req.body.title || req.body.title.trim().length === 0) {
      errors.title = 'Titel ist erforderlich';
    }

    // validate done
    if (typeof req.body.done === 'undefined') {
      errors.done = 'Ende ist erforderlich';
    }

    // validate due date
    if (!req.body.dueDate) {
      errors.dueDate = 'Datum ist erforderlich';
    } else if (!req.body.dueDate.match(/^[0-9]{4}(-[0-9]{2}){2}$/)) {
      errors.dueDate = 'Datum ungültig';
    }

    if (Object.keys(errors).length) {
      res.status(400).json({ errors });
      return false;
    }

    return true;
  }
}

module.exports = IssueController;