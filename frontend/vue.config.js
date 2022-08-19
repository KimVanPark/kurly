const path = require('path');

module.exports = {
  outputDir: '../src/main/resources/static',

  configureWebpack: {
    resolve: {
      alias: {
        '@': path.join(__dirname, 'src/')
      }
    }
  },

  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080/',
        changeOrigin: true,
        pathRewrite: {
          // '^/api': ''
        }
      }
    }
  },
  transpileDependencies: [
    'vuetify'
  ],
  lintOnSave: false
}
