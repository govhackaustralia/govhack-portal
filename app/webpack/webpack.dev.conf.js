var config = require('./index');
var utils = require('./utils');
var webpack = require('webpack');
var merge = require('webpack-merge');
var baseWebpackConfig = require('./webpack.base.conf');

var webpackConfig = merge(baseWebpackConfig, {
    module: {
        loaders: utils.styleLoaders({sourceMap: true, extract: true})
    },
    devtool: 'source-map',
    plugins: [
        new webpack.DefinePlugin({
            'process.env': config.dev.env
        }),
        new webpack.LoaderOptionsPlugin({
            vue: {
                loaders: utils.cssLoaders({
                    sourceMap: true,
                    extract: true
                })
            },
            eslint: {
                formatter: require('eslint-friendly-formatter')
            }
        })
    ]
});

module.exports = webpackConfig;
