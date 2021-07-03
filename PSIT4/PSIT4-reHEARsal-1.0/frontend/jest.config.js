module.exports = {
  preset: '@vue/cli-plugin-unit-jest',
  setupFiles: ['<rootDir>/setEnvVars.js'],
  moduleNameMapper: {
    'client-api': '<rootDir>/src/api/server/index.js',
    'mock-api': '<rootDir>/src/api/mock/index.js',
  },
}
