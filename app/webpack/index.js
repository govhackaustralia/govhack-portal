var path = require('path');

module.exports = {
    build: {
        env: {
            NODE_ENV: '"production"'
        },
        index: path.resolve(__dirname, '../../web/src/main/resources/templates/app/static_import.ftl'),
        assetsRoot: path.resolve(__dirname, '../../web/src/main/resources/resources/app'),
        assetsSubDirectory: '',
        assetsPublicPath: 'resources/app/',
        productionSourceMap: false,
        productionGzip: false,
        productionGzipExtensions: ['js', 'css']
    },
    dev: {
        env: {
            NODE_ENV: '"development"'
        },
        port: 8080,
        assetsSubDirectory: '',
        assetsPublicPath: 'resources/app/',
        cssSourceMap: true
    }
};
