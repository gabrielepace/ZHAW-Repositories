const { EntitySchema } = require('typeorm');
const Project = require('../models/project');

module.exports = new EntitySchema({
  name: 'Project',
  target: Project,
  columns: {
    id: {
      primary: true,
      type: 'int',
      generated: true,
    },
    title: {
      type: 'varchar',
    },
  },
});
