const { createConnection } = require('typeorm');
require('reflect-metadata');

if (!process.env.DATABASE_URL) {
  throw new Error('Environment variable DATABASE_URL not set');
}

// connect to the database
const connect = async () => {
  return await createConnection({
    type: 'postgres',
    url: process.env.DATABASE_URL,
    extra: {
      ssl: true,
    },
    entities: [
      `${__dirname}/schema/*.js`,
    ],
    synchronize: true,
  });
};

module.exports = connect;
