const connectToDatabase = require('./database');
const ProjectController = require('./controllers/project');
const IssueController = require('./controllers/issue');

module.exports = async (app) => {
  const conn = await connectToDatabase();
  const projectController = new ProjectController(conn);
  const issueController = new IssueController(conn);

  app.post('/api/projects', projectController.create);
  app.get('/api/projects/:project', projectController.get);
  app.put('/api/projects/:project', projectController.update);
  app.delete('/api/projects/:project', projectController.delete);

  app.get('/api/projects/:project/issues', issueController.get);
  app.post('/api/projects/:project/issues', issueController.create);
  app.put('/api/projects/:project/issues/:issue', issueController.update);
  app.delete('/api/projects/:project/issues/:issue', issueController.delete);
};
