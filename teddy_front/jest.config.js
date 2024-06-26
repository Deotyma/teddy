/** @type {import('jest').Config} */
const config = {
  clearMocks: true,
  collectCoverage: true,
  coverageDirectory: "coverage",
  testEnvironment: "jest-environment-jsdom",
  transform: {
    '^.+\\.vue$': 'vue3-jest',
    '^.+\\.[tj]sx?$': 'babel-jest',
  },
  moduleFileExtensions: ['js', 'json', 'vue'],
  transformIgnorePatterns: [
    '/node_modules/(?!(axios)/)',
    '\\.pnp\\.[^\\/]+$'
  ],
};

module.exports = config;
