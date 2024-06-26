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
  moduleNameMapper: {
    '\\.(jpg|jpeg|png|gif|svg)$': '<rootDir>/__mocks__/fileMock.js',
  },
};

module.exports = config;
