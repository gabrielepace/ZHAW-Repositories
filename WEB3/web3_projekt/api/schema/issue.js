const { EntitySchema } = require('typeorm');
const Issue = require('../models/issue');

module.exports = new EntitySchema({
  name: 'Issue',
  target: Issue,
  columns: {
    id: {
      primary: true,
      type: 'int',
      generated: true,
    },
    project_id: {
      type: 'int',
    },
    title: {
      type: 'varchar',
    },
    priority: {
      type: 'varchar',
    },
    dueDate: {
      type: 'date',
    },
    done: {
      type: 'boolean',
    },
  },
});
