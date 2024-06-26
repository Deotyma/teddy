module.exports = {
  presets: [
    ['@babel/preset-env', { targets: { node: 'current' } }]
  ],
  plugins: [
    'babel-plugin-transform-vite-meta-env',
    '@babel/plugin-transform-modules-commonjs',
  ],
};